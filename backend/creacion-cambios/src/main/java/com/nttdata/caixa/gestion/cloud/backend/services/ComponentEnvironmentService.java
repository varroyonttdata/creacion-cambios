package com.nttdata.caixa.gestion.cloud.backend.services;

import com.nttdata.caixa.gestion.cloud.backend.entities.ComponentEnvironment;
import com.nttdata.caixa.gestion.cloud.backend.entities.dto.ComponentEnvironmentDTO;
import com.nttdata.caixa.gestion.cloud.backend.exceptions.ComponentEnvironmentException;
import com.nttdata.caixa.gestion.cloud.backend.exceptions.ComponentException;
import com.nttdata.caixa.gestion.cloud.backend.exceptions.EnvironmentException;

public interface ComponentEnvironmentService {

    ComponentEnvironmentDTO createComponentEnvironment(Integer replica, Long componentId, Long environmentId) throws ComponentEnvironmentException, ComponentException, EnvironmentException;

    ComponentEnvironmentDTO updateComponentEnvironment(ComponentEnvironment componentEnvironment) throws ComponentEnvironmentException;

    void deleteComponentEnvironmentById(Long id) throws ComponentEnvironmentException;

    ComponentEnvironmentDTO findById(Long id) throws ComponentEnvironmentException;
}
