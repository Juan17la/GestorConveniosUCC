package com.gucc.GestorConvenioUcc.repository;

import com.gucc.GestorConvenioUcc.entity.ReporteEmpresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReporteEmpresaRepository extends JpaRepository<ReporteEmpresa, Long> {

    /**
     * Busca todos los reportes de un convenio específico
     */
    List<ReporteEmpresa> findByConvenioId(Long convenioId);

    /**
     * Busca todos los reportes de una empresa específica
     */
    List<ReporteEmpresa> findByEmpresaId(Long empresaId);

    /**
     * Busca reportes por empresa y convenio
     */
    List<ReporteEmpresa> findByEmpresaIdAndConvenioId(Long empresaId, Long convenioId);

    /**
     * Cuenta reportes de una empresa en un convenio
     */
    long countByEmpresaIdAndConvenioId(Long empresaId, Long convenioId);

    List<ReporteEmpresa> findByConvenioIdAndEmpresaId(Long convenioId, Long empresaId);
}
