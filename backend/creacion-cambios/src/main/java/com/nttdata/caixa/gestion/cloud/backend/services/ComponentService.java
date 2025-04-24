package com.nttdata.caixa.gestion.cloud.backend.services;

import java.util.List;

import com.nttdata.caixa.gestion.cloud.backend.entities.Component;
import com.nttdata.caixa.gestion.cloud.backend.entities.dto.ComponentDTO;
import com.nttdata.caixa.gestion.cloud.backend.entities.enums.ComponentType;
import com.nttdata.caixa.gestion.cloud.backend.exceptions.ComponentException;

public interface ComponentService {

    ComponentDTO createComponent(Component component);

    ComponentDTO updateComponent(Component component) throws ComponentException;

    void deleteComponentById(Long id) throws ComponentException;

    ComponentDTO findById(Long id) throws ComponentException;

    ComponentDTO findByName(String name) throws ComponentException;

    List<ComponentDTO> findAllByComponentType(ComponentType componentType) throws ComponentException;
}
