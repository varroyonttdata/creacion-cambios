package com.nttdata.caixa.gestion.cloud.backend.services.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.nttdata.caixa.gestion.cloud.backend.entities.Application;
import com.nttdata.caixa.gestion.cloud.backend.entities.Component;
import com.nttdata.caixa.gestion.cloud.backend.entities.ComponentEnvironment;
import com.nttdata.caixa.gestion.cloud.backend.entities.dto.ApplicationDTO;
import com.nttdata.caixa.gestion.cloud.backend.entities.dto.ComponentDTO;
import com.nttdata.caixa.gestion.cloud.backend.entities.enums.ComponentType;
import com.nttdata.caixa.gestion.cloud.backend.exceptions.ApplicationException;
import com.nttdata.caixa.gestion.cloud.backend.exceptions.ComponentEnvironmentException;
import com.nttdata.caixa.gestion.cloud.backend.exceptions.ComponentException;
import com.nttdata.caixa.gestion.cloud.backend.repositories.ComponentEnvironmentRepository;
import com.nttdata.caixa.gestion.cloud.backend.repositories.ComponentRepository;
import com.nttdata.caixa.gestion.cloud.backend.services.ComponentService;


@SuppressWarnings("unused")
public class ComponentServiceImpl implements ComponentService {

    private static final Logger logger = LogManager.getLogger(ComponentServiceImpl.class);

    private final ComponentRepository componentRepository;
    private ModelMapper mapper;

    public ComponentServiceImpl(ComponentRepository componentRepository, ModelMapper mapper) {
        this.componentRepository = componentRepository;
        this.mapper = mapper;

    }

    @Override
    @Transactional
    public ComponentDTO createComponent(Component component) {
        Component saved = componentRepository.save(component);
        logger.info("Componente creado: " + saved);
        return this.changeToComponentDTO(saved);
    }

    @Override
    @Transactional
    public ComponentDTO updateComponent(Component component) throws ComponentException {
        Component searched = componentRepository.findById(component.getId()).orElseThrow(() -> new ComponentException("No existe el componente con id: " + component.getId()));
        searched.setName(component.getName());
        searched.setComponentType(component.getComponentType());
        Component updated = componentRepository.save(searched);
        logger.info("Componente actualizado: " + updated);
        return this.changeToComponentDTO(updated);
    }

    @Override
    @Transactional
    public void deleteComponentById(Long id) throws ComponentException {
        Component searched = componentRepository.findById(id).orElseThrow(() -> new ComponentException("No existe el componente con id: " + id));
        componentRepository.delete(searched);
        logger.info("Componente borrado: " + searched);
    }

    @Override
    public ComponentDTO findById(Long id) throws ComponentException {
        Component component = componentRepository.findById(id).orElseThrow(() -> new ComponentException("El componente con id " + id + " no existe"));
        return this.changeToComponentDTO(component);
    }

    @Override
    public ComponentDTO findByName(String name) throws ComponentException {
        logger.info("Buscando componente por nombre: " + name);
        Component searched = componentRepository.findByName(name).orElseThrow(() -> new ComponentException("No se ha encontrado el componente con este nombre: " + name));
        return this.changeToComponentDTO(searched);
    }

    @Override
    public List<ComponentDTO> findAllByComponentType(ComponentType componentType) throws ComponentException {
        logger.info("Buscando componentes por tipo: " + componentType);
        List<Component> searched = componentRepository.findAllByComponentType(componentType);
        if (searched.isEmpty()) {
            throw new ComponentException("No se ha encontrado ningun componente con este tipo: " + componentType);
        }
        logger.info("Componentes encontrados: " + searched);
        return changeListToComponentDTOs(searched);
    }

    private ComponentDTO changeToComponentDTO (Component component) {
        return this.mapper.map(component, ComponentDTO.class);
    }

    private List<ComponentDTO> changeListToComponentDTOs (List<Component> component) {
        List<ComponentDTO> listDTO = component.stream().map(comp -> this.changeToComponentDTO(comp)).collect(Collectors.toList());
        return listDTO;
    }


}
