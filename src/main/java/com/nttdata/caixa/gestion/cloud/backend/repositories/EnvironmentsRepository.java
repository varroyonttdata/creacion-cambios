package com.nttdata.caixa.gestion.cloud.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nttdata.caixa.gestion.cloud.backend.entities.Environments;

public interface EnvironmentsRepository extends JpaRepository<Environments, Long> {

}
