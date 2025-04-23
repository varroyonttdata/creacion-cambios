package com.nttdata.caixa.gestion.cloud.backend.services;

import java.util.Optional;

import com.nttdata.caixa.gestion.cloud.backend.entities.Environment;
import com.nttdata.caixa.gestion.cloud.backend.entities.dto.EnvironmentDTO;
import com.nttdata.caixa.gestion.cloud.backend.entities.enums.EnvironmentType;
import com.nttdata.caixa.gestion.cloud.backend.exceptions.EnvironmentException;


public interface EnvironmentService {

    EnvironmentDTO findById(Long id) throws EnvironmentException;

    EnvironmentDTO createEnvironmentDTO(Environment environment) throws EnvironmentException;

    EnvironmentDTO updateEnvironment(Environment environment) throws EnvironmentException;

    void deleteEnvironmentById(Long id) throws EnvironmentException;

    Optional<Environment> findByEnvironmentType(EnvironmentType environmentType);

    Environment createEnvironment(Environment environment);

}
