package com.nttdata.caixa.gestion.cloud.backend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.caixa.gestion.cloud.backend.entities.Component;
import com.nttdata.caixa.gestion.cloud.backend.entities.enums.ComponentType;

@Repository
public interface ComponentRepository extends JpaRepository <Component, Long> {
    
    Optional<Component> findByName(String name);

    List<Component> findAllByComponentType(ComponentType componentType);

}
