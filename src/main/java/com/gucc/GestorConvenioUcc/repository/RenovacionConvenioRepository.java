package com.gucc.GestorConvenioUcc.repository;

import com.gucc.GestorConvenioUcc.entity.RenovacionConvenio;
import com.gucc.GestorConvenioUcc.enums.TipoRenovacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RenovacionConvenioRepository extends JpaRepository<RenovacionConvenio, Long> {
    List<RenovacionConvenio> findByConvenioOriginalId(Long convenioId);
    List<RenovacionConvenio> findByAccion(TipoRenovacion accion);
    List<RenovacionConvenio> findByNuevaPeticionId(Long peticionId);
}
