package com.gucc.GestorConvenioUcc.repository;

import com.gucc.GestorConvenioUcc.entity.AlertaVencimiento;
import com.gucc.GestorConvenioUcc.enums.EstadoAlerta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AlertaVencimientoRepository extends JpaRepository<AlertaVencimiento, Long> {
    List<AlertaVencimiento> findByConvenioId(Long convenioId);
    List<AlertaVencimiento> findByEnviadaAId(Long usuarioId);
    List<AlertaVencimiento> findByEstado(EstadoAlerta estado);
    List<AlertaVencimiento> findByEnviadaAIdAndEstado(Long usuarioId, EstadoAlerta estado);
}
