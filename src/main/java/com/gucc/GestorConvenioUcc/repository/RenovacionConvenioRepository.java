package com.gucc.GestorConvenioUcc.repository;

import com.gucc.GestorConvenioUcc.entity.RenovacionConvenio;
import com.gucc.GestorConvenioUcc.enums.EstadoPeticion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RenovacionConvenioRepository extends JpaRepository<RenovacionConvenio, Long> {
    List<RenovacionConvenio> findByConvenioOriginalId(Long convenioId);
    List<RenovacionConvenio> findByNuevaPeticionId(Long peticionId);
    boolean existsByConvenioOriginalId(Long convenioId);

    RenovacionConvenio findTopByConvenioOriginalIdOrderByFechaCreacionDesc(Long id);
}
