package com.gucc.GestorConvenioUcc.repository;

import com.gucc.GestorConvenioUcc.entity.RevisionJuridica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface RevisionJuridicaRepository extends JpaRepository<RevisionJuridica, Long> {
    Optional<RevisionJuridica> findByPeticionId(Long peticionId);
    List<RevisionJuridica> findByRevisorId(Long revisorId);
    List<RevisionJuridica> findByAprobada(Boolean aprobada);
}
