package com.nttdata.caixa.gestion.cloud.backend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.caixa.gestion.cloud.backend.entities.Application;
import com.nttdata.caixa.gestion.cloud.backend.entities.enums.AppType;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    Optional<Application> findByName(String name);
    
    List<Application> findAllByAppType(AppType appType);

    Optional<Application> findApplicationByComponentId(Long id);
}
