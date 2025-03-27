package com.nttdata.caixa.gestion.cloud.backend.services;

import com.nttdata.caixa.gestion.cloud.backend.entities.Environments;
import com.nttdata.caixa.gestion.cloud.backend.entities.enums.Environment;

public interface EnvironmentsService {

    Environments findById(Long id);

    Environments findByEnvironment(Environment environment);

}
