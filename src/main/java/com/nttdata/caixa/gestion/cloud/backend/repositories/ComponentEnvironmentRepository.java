package com.nttdata.caixa.gestion.cloud.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.caixa.gestion.cloud.backend.entities.ComponentEnvironment;

@Repository
public interface ComponentEnvironmentRepository extends JpaRepository<ComponentEnvironment, Long> {

    List<ComponentEnvironment> findByComponentId(Long componentId);
    
    List<ComponentEnvironment> findByEnvironmentId(Long environmentId);

}
