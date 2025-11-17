package com.gucc.GestorConvenioUcc.repository;

import com.gucc.GestorConvenioUcc.entity.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Long> {
    List<Documento> findByPeticionId(Long peticionId);
    List<Documento> findByConvenioId(Long convenioId);
    List<Documento> findBySubidoPorId(Long usuarioId);
}
