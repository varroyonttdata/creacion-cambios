package com.nttdata.caixa.gestion.cloud.backend.services.implementations;

import com.nttdata.caixa.gestion.cloud.backend.entities.Applications;
import com.nttdata.caixa.gestion.cloud.backend.entities.Environments;
import com.nttdata.caixa.gestion.cloud.backend.entities.dto.EnvironmentsDTO;
import com.nttdata.caixa.gestion.cloud.backend.entities.enums.Environment;
import com.nttdata.caixa.gestion.cloud.backend.exceptions.ApplicationsException;
import com.nttdata.caixa.gestion.cloud.backend.exceptions.EnvironmentsException;
import com.nttdata.caixa.gestion.cloud.backend.repositories.EnvironmentsRepository;
import com.nttdata.caixa.gestion.cloud.backend.services.EnvironmentsService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;


public class EnvironmentsServiceImpl implements EnvironmentsService {

    private static final Logger logger = LogManager.getLogger(ApplicationsServiceImpl.class);

    private final EnvironmentsRepository environmentsRepository;
    private final ApplicationsServiceImpl applicationsServiceImpl;
    private ModelMapper mapper;

    public EnvironmentsServiceImpl(EnvironmentsRepository environmentsRepository,
            ApplicationsServiceImpl applicationsServiceImpl, ModelMapper mapper) {
        this.environmentsRepository = environmentsRepository;
        this.applicationsServiceImpl = applicationsServiceImpl;
        this.mapper = mapper;
    }

    @Override
    public EnvironmentsDTO findById(Long id) throws EnvironmentsException {
        Environments searched = environmentsRepository.findById(id).orElseThrow(() -> new EnvironmentsException("El entorno no existe"));
        return this.changeToEnvironmentsDTO(searched);
    }

    public List<EnvironmentsDTO> findAllByEnvironment(String environment) throws EnvironmentsException {
        try { 
            logger.info("Buscando entornos por entorno: " + environment);
            environment = environment.toUpperCase();
            Environment enumEnvironment = Environment.valueOf(environment);
            List<Environments> searched = this.environmentsRepository.findAllByEnvironment(enumEnvironment);
            return this.changeListToEnvironmentsDTO(searched);
        } catch (IllegalArgumentException e) {
            throw new EnvironmentsException("El entorno no existe");
        }
        
    }

    @Override
    public EnvironmentsDTO createEnvironments(Environments environments) throws EnvironmentsException {
        final Environments saved = this.environmentsRepository.save(environments);
        logger.info("Entorno creado: " + saved);
        return this.changeToEnvironmentsDTO(saved);

    }

    //TODO Metodo createEnvironments no agrega dependencia application_id al environments, ver que pasa. update seguramente tp funcione.
    // public Environments createtest(Environments environments, Long id) throws EnvironmentsException, ApplicationsException {
    //     Applications searched = this.applicationsServiceImpl.findById(id);
    //     Environments saved = this.environmentsRepository.save(environments);
    //     searched.setEnvironments(Arrays.asList(saved));
    //     environments.setApplications(searched);


    //     logger.info("Entorno creado: " + saved);
    //     return saved;
    // }

    @Override
    public EnvironmentsDTO updateEnvironments(Environments environments) throws EnvironmentsException {
        Environments toUpdate = environmentsRepository.findById(environments.getId()).orElseThrow(() -> new EnvironmentsException("El entorno no existe con id: " + environments.getId()));
        logger.trace("Se va a modificar el entorno: " + toUpdate);
        toUpdate.setEnvironment(environments.getEnvironment());
        Environments saved = this.environmentsRepository.save(toUpdate);
        logger.trace("Se ha modificado el entorno: " + saved);
        return this.changeToEnvironmentsDTO(saved);
    }

    @Override
    public void deleteEnvironmentsById(Long id) throws EnvironmentsException {
        Environments searched = environmentsRepository.findById(id).orElseThrow(() -> new EnvironmentsException("El entorno no existe con id: " + id ));
        logger.info("Se va a borrar el entorno " + id);
        this.environmentsRepository.delete(searched);
    }

    private EnvironmentsDTO changeToEnvironmentsDTO (Environments environments) {
        return this.mapper.map(environments, EnvironmentsDTO.class);
    }

    private List<EnvironmentsDTO> changeListToEnvironmentsDTO (List<Environments> environments) {
        List<EnvironmentsDTO> listDTO = environments.stream().map(env -> this.changeToEnvironmentsDTO(env)).collect(Collectors.toList());
        return listDTO;
    }

}
