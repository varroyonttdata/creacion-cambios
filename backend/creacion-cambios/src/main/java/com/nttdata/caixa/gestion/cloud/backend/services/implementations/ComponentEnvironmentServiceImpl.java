package com.nttdata.caixa.gestion.cloud.backend.services.implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;

import com.nttdata.caixa.gestion.cloud.backend.entities.Component;
import com.nttdata.caixa.gestion.cloud.backend.entities.ComponentEnvironment;
import com.nttdata.caixa.gestion.cloud.backend.entities.Environment;
import com.nttdata.caixa.gestion.cloud.backend.entities.dto.ComponentEnvironmentDTO;
import com.nttdata.caixa.gestion.cloud.backend.exceptions.ComponentEnvironmentException;
import com.nttdata.caixa.gestion.cloud.backend.exceptions.ComponentException;
import com.nttdata.caixa.gestion.cloud.backend.exceptions.EnvironmentException;
import com.nttdata.caixa.gestion.cloud.backend.repositories.ComponentEnvironmentRepository;
import com.nttdata.caixa.gestion.cloud.backend.repositories.ComponentRepository;
import com.nttdata.caixa.gestion.cloud.backend.repositories.EnvironmentRepository;
import com.nttdata.caixa.gestion.cloud.backend.services.ComponentEnvironmentService;

public class ComponentEnvironmentServiceImpl implements ComponentEnvironmentService {

    private static final Logger logger = LogManager.getLogger(ComponentEnvironmentServiceImpl.class);

    private final ComponentRepository componentRepository;
    private final EnvironmentRepository environmentRepository;
    private final ComponentEnvironmentRepository componentEnvironmentRepository;
    private ModelMapper mapper;

    public ComponentEnvironmentServiceImpl(ComponentRepository componentRepository, EnvironmentRepository environmentRepository, ComponentEnvironmentRepository componentEnvironmentRepository, ModelMapper mapper) {
        this.componentRepository = componentRepository;
        this.environmentRepository = environmentRepository;
        this.componentEnvironmentRepository = componentEnvironmentRepository;
        this.mapper = mapper;
    }

    @Override
    public ComponentEnvironmentDTO createComponentEnvironment(Integer replica, Long componentId, Long environmentId) throws ComponentEnvironmentException, ComponentException, EnvironmentException{
        ComponentEnvironment created = new ComponentEnvironment(replica);
        componentEnvironmentRepository.save(created);
        logger.info("Componente creado: " + created);
        ComponentEnvironment saved = addComponentAndEnvironmentToComponentEnvironment(created, componentId, environmentId);
        return this.changeToComponentEnvironmentDTO(saved);
    }

    @Override
    public ComponentEnvironmentDTO updateComponentEnvironment(ComponentEnvironment componentEnvironment) throws ComponentEnvironmentException {
        ComponentEnvironment searched = componentEnvironmentRepository.findById(componentEnvironment.getId()).orElseThrow(() -> new ComponentEnvironmentException("No existe el componente con id: " + componentEnvironment.getId()));
        logger.info("Actualizando componente: " + componentEnvironment);
        searched.setComponent(componentEnvironment.getComponent());
        searched.setEnvironment(componentEnvironment.getEnvironment());
        searched.setReplica(componentEnvironment.getReplica());
        componentEnvironmentRepository.save(searched);
        logger.info("Componente actualizado: " + searched);
        return this.changeToComponentEnvironmentDTO(searched);
    }

    @Override
    public void deleteComponentEnvironmentById(Long id) throws ComponentEnvironmentException {
        ComponentEnvironment searched = componentEnvironmentRepository.findById(id).orElseThrow(() -> new ComponentEnvironmentException("No existe el componente con id: " + id));
        logger.info("Se va a borrar el componente: " + searched);
        componentEnvironmentRepository.delete(searched);

    }

    @Override
    public ComponentEnvironmentDTO findById(Long id) throws ComponentEnvironmentException {
        ComponentEnvironment searched = componentEnvironmentRepository.findById(id).orElseThrow(() -> new ComponentEnvironmentException("No existe el componente con id: " + id));
        logger.info("Componente encontrado: " + searched);
        return this.changeToComponentEnvironmentDTO(searched);
    }
    
    private ComponentEnvironment addComponentAndEnvironmentToComponentEnvironment(ComponentEnvironment componentEnvironment, Long componentId, Long environmentId) throws ComponentEnvironmentException, ComponentException, EnvironmentException {
        ComponentEnvironment searched = componentEnvironmentRepository.findById(componentEnvironment.getId()).orElseThrow(() -> new ComponentEnvironmentException("No existe el componente con id: " + componentEnvironment.getId()));
        Component compSearched = componentRepository.findById(componentId).orElseThrow(() -> new ComponentException("No existe el componente con id: " + componentId));
        Environment envSearched = environmentRepository.findById(environmentId).orElseThrow(() -> new EnvironmentException("No existe el entorno con id: " + environmentId));
        logger.info("Componente encontrado: " + searched);
        searched.setComponent(compSearched);
        searched.setEnvironment(envSearched);
        searched.setReplica(componentEnvironment.getReplica());
        componentEnvironmentRepository.save(searched);
        logger.info("Componente actualizado: " + searched);
        return searched;
    }

    private ComponentEnvironmentDTO changeToComponentEnvironmentDTO (ComponentEnvironment componentEnvironment) {
        return this.mapper.map(componentEnvironment, ComponentEnvironmentDTO.class);
    }

    @SuppressWarnings("unused")
    private List<ComponentEnvironmentDTO> changeListToComponentEnvironmentDTOs (List<ComponentEnvironment> componentEnvironment) {
        List<ComponentEnvironmentDTO> listDTO = componentEnvironment.stream().map(compenv -> this.changeToComponentEnvironmentDTO(compenv)).collect(Collectors.toList());
        return listDTO;
    }


}
