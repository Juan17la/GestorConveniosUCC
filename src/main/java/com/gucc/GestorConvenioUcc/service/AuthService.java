package com.gucc.GestorConvenioUcc.service;

import com.gucc.GestorConvenioUcc.dto.AuthResponse;
import com.gucc.GestorConvenioUcc.dto.LoginRequest;
import com.gucc.GestorConvenioUcc.dto.RegisterRequest;

public interface AuthService {
    AuthResponse login(LoginRequest request);
    AuthResponse register(RegisterRequest request);
}
