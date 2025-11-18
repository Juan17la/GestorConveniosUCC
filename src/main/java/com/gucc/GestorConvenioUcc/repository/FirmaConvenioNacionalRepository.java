package com.gucc.GestorConvenioUcc.repository;

import com.gucc.GestorConvenioUcc.entity.FirmaConvenioNacional;
import com.gucc.GestorConvenioUcc.entity.Peticion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface FirmaConvenioNacionalRepository extends JpaRepository<FirmaConvenioNacional, Long> {
    Optional<FirmaConvenioNacional> findByPeticionId(Long peticionId);
    List<FirmaConvenioNacional> findByRevisorId(Long revisorId);
    List<FirmaConvenioNacional> findByAprobada(Boolean aprobada);

    boolean existsByPeticion(Peticion peticion);
}
