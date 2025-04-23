package com.nttdata.caixa.gestion.cloud.backend.repositories;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.caixa.gestion.cloud.backend.entities.Environment;
import com.nttdata.caixa.gestion.cloud.backend.entities.enums.EnvironmentType;

@Repository
public interface EnvironmentRepository extends JpaRepository<Environment, Long> {

    List<Environment> findAllByEnvironmentType(EnvironmentType environmentType);

    Optional<Environment> findByEnvironmentType(EnvironmentType environmentType);
}
