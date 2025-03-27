package com.nttdata.caixa.gestion.cloud.backend.services;

import com.nttdata.caixa.gestion.cloud.backend.entities.Applications;
import com.nttdata.caixa.gestion.cloud.backend.entities.Environments;
import com.nttdata.caixa.gestion.cloud.backend.entities.enums.Type;

public interface ApplicationsService {

    Applications findById(Long id);

    Applications findByName(String name);

    Applications findByType(Type type);

    Applications findApplicationsByEnvironments (Environments environments);
}
