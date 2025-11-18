package com.gucc.GestorConvenioUcc.repository;

import com.gucc.GestorConvenioUcc.entity.Peticion;
import com.gucc.GestorConvenioUcc.enums.EstadoPeticion;
import com.gucc.GestorConvenioUcc.enums.TipoConvenio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PeticionRepository extends JpaRepository<Peticion, Long> {
    List<Peticion> findByCreadoPorId(Long usuarioId);
    List<Peticion> findByCampusId(Long campusId);
    List<Peticion> findByTipo(TipoConvenio tipo);
    List<Peticion> findByCampusIdAndTipo(Long campusId, TipoConvenio tipo);
    boolean existsByConvenioIdAndEstadoIn(Long convenioId, List<EstadoPeticion> estadosBloqueantes);
    /**
     * Busca peticiones de renovación que no estén aprobadas para un convenio específico
     * Útil para verificar si hay renovaciones en curso
     */
    @Query("SELECT p FROM Peticion p JOIN p.convenio c " +
            "WHERE c.id = :convenioId AND p.estado != :estado")
    List<Peticion> findByConvenioIdAndEstadoNot(
            @Param("convenioId") Long convenioId,
            @Param("estado") EstadoPeticion estado
    );
    /**
     * Busca todas las peticiones relacionadas con un convenio
     * Esto incluye la petición de creación y cualquier renovación
     */
    @Query("SELECT p FROM Peticion p WHERE p.convenio.id = :convenioId")
    List<Peticion> findByConvenioId(@Param("convenioId") Long convenioId);
}
