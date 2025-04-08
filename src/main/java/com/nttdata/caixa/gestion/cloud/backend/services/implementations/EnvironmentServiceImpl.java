package com.nttdata.caixa.gestion.cloud.backend.services.implementations;


import com.nttdata.caixa.gestion.cloud.backend.entities.Environment;
import com.nttdata.caixa.gestion.cloud.backend.entities.dto.EnvironmentDTO;
import com.nttdata.caixa.gestion.cloud.backend.entities.enums.EnvironmentType;

import com.nttdata.caixa.gestion.cloud.backend.exceptions.EnvironmentException;
import com.nttdata.caixa.gestion.cloud.backend.repositories.EnvironmentRepository;
import com.nttdata.caixa.gestion.cloud.backend.services.EnvironmentService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;


public class EnvironmentServiceImpl implements EnvironmentService {

    private static final Logger logger = LogManager.getLogger(ComponentServiceImpl.class);

    private final EnvironmentRepository environmentRepository;
    private ModelMapper mapper;

    public EnvironmentServiceImpl(EnvironmentRepository environmentRepository, ModelMapper mapper) {
        this.environmentRepository = environmentRepository;
        this.mapper = mapper;
    }

    @Override
    public EnvironmentDTO findById(Long id) throws EnvironmentException {
        Environment searched = environmentRepository.findById(id).orElseThrow(() -> new EnvironmentException("El entorno no existe"));
        return this.changeToEnvironmentDTO(searched);
    }

    public List<EnvironmentDTO> findAllByEnvironmentType(String environment) throws EnvironmentException {
        try { 
            logger.info("Buscando entornos por entorno: " + environment);
            environment = environment.toUpperCase();
            EnvironmentType enumEnvironment = EnvironmentType.valueOf(environment);
            List<Environment> searched = this.environmentRepository.findAllByEnvironmentType(enumEnvironment);
            return this.changeListToEnvironmentsDTO(searched);
        } catch (IllegalArgumentException e) {
            throw new EnvironmentException("El entorno no existe");
        }
        
    }

    @Transactional
    @Override
    public EnvironmentDTO createEnvironmentDTO(Environment environment) throws EnvironmentException {
        final Environment saved = this.environmentRepository.save(environment);
        logger.info("Entorno creado: " + saved);
        return this.changeToEnvironmentDTO(saved);

    }

    //Esto sirve para algo despues de modificar el servicio????

    // public EnvironmentDTO createEnvironmentsWithComponent(ComponentEnvironment componentEnvironment, Long id) throws EnvironmentException, ComponentException {
    //     Component component = this.componentRepository.findById(id).orElseThrow(() -> new ComponentException("No se ha encontrado el id: " + id));
    //     if (component.getEnvironment().isEmpty()) {
    //         component.setEnvironment(new ArrayList<Environment>());
    //         logger.trace("Creada lista de entornos vacia y asignada a aplicación");
    //     }
    //     List<Environment> envList = component.getEnvironment();
    //     envList.add(environment);
    //     component.setEnvironment(envList);
    //     logger.info("Actualizada la lista de entornos de la aplicación :" + envList);
    //     environment.setComponent(component);
    //     logger.info("Asignada aplicación al entorno");
    //     this.componentRepository.save(component);
    //     Environment saved = this.environmentRepository.save(environment);
    //     return this.changeToEnvironmentDTO(saved);
    // }

    @Transactional
    @Override
    public EnvironmentDTO updateEnvironment(Environment environment) throws EnvironmentException {
        Environment toUpdate = environmentRepository.findById(environment.getId()).orElseThrow(() -> new EnvironmentException("El entorno no existe con id: " + environment.getId()));
        logger.trace("Se va a modificar el entorno: " + toUpdate);
        toUpdate.setEnvironmentType(environment.getEnvironmentType());
        Environment saved = this.environmentRepository.save(toUpdate);
        logger.trace("Se ha modificado el entorno: " + saved);
        return this.changeToEnvironmentDTO(saved);
    }

    @Transactional
    @Override
    public void deleteEnvironmentById(Long id) throws EnvironmentException {
        Environment searched = environmentRepository.findById(id).orElseThrow(() -> new EnvironmentException("El entorno no existe con id: " + id ));
        logger.info("Se va a borrar el entorno " + id);
        this.environmentRepository.delete(searched);
    }

    private EnvironmentDTO changeToEnvironmentDTO (Environment environment) {
        return this.mapper.map(environment, EnvironmentDTO.class);
    }

    private List<EnvironmentDTO> changeListToEnvironmentsDTO (List<Environment> environment) {
        List<EnvironmentDTO> listDTO = environment.stream().map(env -> this.changeToEnvironmentDTO(env)).collect(Collectors.toList());
        return listDTO;
    }

    @Override
    public Optional<Environment> findByEnvironmentType(EnvironmentType environmentType) {
        return this.environmentRepository.findByEnvironmentType(environmentType);
    }

    @Override
    public Environment createEnvironment(Environment environment) {
        return this.environmentRepository.save(environment);
    }

}
