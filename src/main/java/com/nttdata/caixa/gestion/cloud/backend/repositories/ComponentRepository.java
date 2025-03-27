package com.nttdata.caixa.gestion.cloud.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nttdata.caixa.gestion.cloud.backend.entities.Component;

public interface ComponentRepository extends JpaRepository <Component, Long> {

}
