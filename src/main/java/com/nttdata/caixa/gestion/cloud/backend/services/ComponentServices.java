package com.nttdata.caixa.gestion.cloud.backend.services;

import com.nttdata.caixa.gestion.cloud.backend.entities.Component;
import com.nttdata.caixa.gestion.cloud.backend.entities.enums.ComponentType;

public interface ComponentServices {

    Component findById(Long id);

    Component findByName(String name);

    Component findByComponentType(ComponentType componentType);
}
