package com.gucc.GestorConvenioUcc.repository;

import com.gucc.GestorConvenioUcc.entity.ReporteEmpresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReporteEmpresaRepository extends JpaRepository<ReporteEmpresa, Long> {
    List<ReporteEmpresa> findByConvenioId(Long convenioId);
    List<ReporteEmpresa> findByEmpresaId(Long empresaId);
    List<ReporteEmpresa> findByConvenioIdAndEmpresaId(Long convenioId, Long empresaId);
}
