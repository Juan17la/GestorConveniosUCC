package com.gucc.GestorConvenioUcc.repository;

import com.gucc.GestorConvenioUcc.entity.Campus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CampusRepository extends JpaRepository<Campus, Long> {
    Optional<Campus> findByNombre(String nombre);
}
