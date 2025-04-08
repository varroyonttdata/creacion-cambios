package com.nttdata.caixa.gestion.cloud.backend.services;

import com.nttdata.caixa.gestion.cloud.backend.entities.ComponentEnvironment;
import com.nttdata.caixa.gestion.cloud.backend.entities.dto.ComponentEnvironmentDTO;
import com.nttdata.caixa.gestion.cloud.backend.exceptions.ComponentEnvironmentException;

public interface ComponentEnvironmentService {

    ComponentEnvironmentDTO createComponentEnvironment(ComponentEnvironment componentEnvironment);

    ComponentEnvironmentDTO updateComponentEnvironment(ComponentEnvironment componentEnvironment) throws ComponentEnvironmentException;

    void deleteComponentEnvironmentById(Long id) throws ComponentEnvironmentException;

    ComponentEnvironmentDTO findById(Long id) throws ComponentEnvironmentException;
}
