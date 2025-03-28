package com.nttdata.caixa.gestion.cloud.backend.services;

import com.nttdata.caixa.gestion.cloud.backend.entities.Environments;
import com.nttdata.caixa.gestion.cloud.backend.exceptions.EnvironmentsException;


public interface EnvironmentsService {

    Environments findById(Long id) throws EnvironmentsException;

}
