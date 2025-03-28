package com.nttdata.caixa.gestion.cloud.backend.repositories;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nttdata.caixa.gestion.cloud.backend.entities.Environments;
import com.nttdata.caixa.gestion.cloud.backend.entities.enums.Environment;

public interface EnvironmentsRepository extends JpaRepository<Environments, Long> {

    List<Environments> findAllByEnvironment(Environment environment);
}
