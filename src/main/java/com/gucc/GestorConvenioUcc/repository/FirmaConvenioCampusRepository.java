package com.gucc.GestorConvenioUcc.repository;

import com.gucc.GestorConvenioUcc.entity.FirmaConvenioCampus;
import com.gucc.GestorConvenioUcc.entity.Peticion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface FirmaConvenioCampusRepository extends JpaRepository<FirmaConvenioCampus, Long> {
    Optional<FirmaConvenioCampus> findByPeticionId(Long peticionId);
    List<FirmaConvenioCampus> findByRevisorId(Long revisorId);
    List<FirmaConvenioCampus> findByAprobada(Boolean aprobada);

    boolean existsByPeticion(Peticion peticion);
}
