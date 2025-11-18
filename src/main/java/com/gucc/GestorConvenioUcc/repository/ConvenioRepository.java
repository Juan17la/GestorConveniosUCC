package com.gucc.GestorConvenioUcc.repository;

import com.gucc.GestorConvenioUcc.entity.Convenio;
import com.gucc.GestorConvenioUcc.enums.EstadoConvenio;
import com.gucc.GestorConvenioUcc.enums.TipoConvenio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ConvenioRepository extends JpaRepository<Convenio, Long> {
    List<Convenio> findByCreadoPorId(Long usuarioId);
    List<Convenio> findByCampusId(Long campusId);
    List<Convenio> findByTipo(TipoConvenio tipo);
    List<Convenio> findByEstado(EstadoConvenio estado);
    List<Convenio> findByCampusIdAndEstado(Long campusId, EstadoConvenio estado);
    List<Convenio> findByEmpresaId(Long empresaId);
    List<Convenio> findBySupervisadoPorId(Long usuarioId);

    List<Convenio> findByEstadoIn(List<EstadoConvenio> activo);
}
