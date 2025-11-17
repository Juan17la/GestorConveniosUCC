package com.gucc.GestorConvenioUcc.repository;

import com.gucc.GestorConvenioUcc.entity.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    Optional<Empresa> findByNit(String nit);
    Optional<Empresa> findByNombre(String nombre);
}
