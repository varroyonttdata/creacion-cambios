package com.nttdata.caixa.gestion.cloud.backend.services;

import com.nttdata.caixa.gestion.cloud.backend.entities.Environments;
import com.nttdata.caixa.gestion.cloud.backend.entities.dto.EnvironmentsDTO;
import com.nttdata.caixa.gestion.cloud.backend.exceptions.EnvironmentsException;


public interface EnvironmentsService {

    EnvironmentsDTO findById(Long id) throws EnvironmentsException;

    EnvironmentsDTO createEnvironments(Environments environments) throws EnvironmentsException;

    EnvironmentsDTO updateEnvironments(Environments environments) throws EnvironmentsException;

    void deleteEnvironmentsById(Long id) throws EnvironmentsException;

}
