package com.gucc.GestorConvenioUcc.service.impl;

import com.gucc.GestorConvenioUcc.dto.AuthResponse;
import com.gucc.GestorConvenioUcc.dto.LoginRequest;
import com.gucc.GestorConvenioUcc.dto.RegisterRequest;
import com.gucc.GestorConvenioUcc.entity.Campus;
import com.gucc.GestorConvenioUcc.entity.Usuario;
import com.gucc.GestorConvenioUcc.enums.Rol;
import com.gucc.GestorConvenioUcc.repository.CampusRepository;
import com.gucc.GestorConvenioUcc.repository.UsuarioRepository;
import com.gucc.GestorConvenioUcc.service.AuthService;
import com.gucc.GestorConvenioUcc.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UsuarioRepository usuarioRepository;
    private final CampusRepository campusRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getContrasena())
        );
        
        Usuario usuario = usuarioRepository.findByUsername(request.getUsername())
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            
        String token = jwtService.getToken(usuario);
        
        return AuthResponse.builder()
            .token(token)
            .username(usuario.getUsername())
            .rol(usuario.getRol().name())
            .campusId(usuario.getCampus() != null ? usuario.getCampus().getId() : null)
            .build();
    }

    @Override
    public AuthResponse register(RegisterRequest request) {
        if (usuarioRepository.existsByUsername(request.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre de usuario ya está en uso");
        }

        // Buscar el campus por ID o lanzar una excepción si no se encuentra
        Campus campus = campusRepository.findById(request.getCampusId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se encontró el campus con ID: " + request.getCampusId()));

        // Crear y guardar el nuevo usuario
        Usuario usuario = Usuario.builder()
            .nombre(request.getNombre())
            .username(request.getUsername())
            .contrasena(passwordEncoder.encode(request.getContrasena()))
            .rol(request.getRol() != null ? request.getRol() : Rol.COMUN)
            .campus(campus)
            .build();

        usuario = usuarioRepository.save(usuario);

        // Generar el token JWT
        String token = jwtService.getToken(usuario);

        return AuthResponse.builder()
            .token(token)
            .username(usuario.getUsername())
            .rol(usuario.getRol().name())
            .campusId(usuario.getCampus().getId())
            .build();
    }
}
