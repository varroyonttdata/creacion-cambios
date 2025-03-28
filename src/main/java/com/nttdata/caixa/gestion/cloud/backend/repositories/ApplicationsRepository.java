package com.nttdata.caixa.gestion.cloud.backend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.caixa.gestion.cloud.backend.entities.Applications;
import com.nttdata.caixa.gestion.cloud.backend.entities.enums.Type;

@Repository
public interface ApplicationsRepository extends JpaRepository<Applications, Long> {

    Optional<Applications> findByName(String name);
    
    List<Applications> findAllByType(Type type);

    Optional<Applications> findApplicationsByEnvironmentsId(Long id);
}
