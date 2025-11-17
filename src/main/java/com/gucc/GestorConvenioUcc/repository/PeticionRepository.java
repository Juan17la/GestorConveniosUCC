package com.gucc.GestorConvenioUcc.repository;

import com.gucc.GestorConvenioUcc.entity.Peticion;
import com.gucc.GestorConvenioUcc.enums.TipoConvenio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PeticionRepository extends JpaRepository<Peticion, Long> {
    List<Peticion> findByCreadoPorId(Long usuarioId);
    List<Peticion> findByCampusId(Long campusId);
    List<Peticion> findByTipo(TipoConvenio tipo);
    List<Peticion> findByCampusIdAndTipo(Long campusId, TipoConvenio tipo);
}
