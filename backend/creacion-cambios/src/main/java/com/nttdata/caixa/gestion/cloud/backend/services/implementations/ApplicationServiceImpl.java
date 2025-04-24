package com.nttdata.caixa.gestion.cloud.backend.services.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;

import com.nttdata.caixa.gestion.cloud.backend.entities.Application;
import com.nttdata.caixa.gestion.cloud.backend.entities.Component;
import com.nttdata.caixa.gestion.cloud.backend.entities.dto.ApplicationDTO;
import com.nttdata.caixa.gestion.cloud.backend.entities.enums.AppType;
import com.nttdata.caixa.gestion.cloud.backend.exceptions.ApplicationException;
import com.nttdata.caixa.gestion.cloud.backend.repositories.ApplicationRepository;
import com.nttdata.caixa.gestion.cloud.backend.repositories.ComponentRepository;
import com.nttdata.caixa.gestion.cloud.backend.services.ApplicationService;



public class ApplicationServiceImpl implements ApplicationService {

    private static final Logger logger = LogManager.getLogger(ApplicationServiceImpl.class);

    private ModelMapper mapper;
    private ApplicationRepository applicationRepository;
    private ComponentRepository componentRepository;

    public ApplicationServiceImpl(ModelMapper mapper, ApplicationRepository applicationRepository, ComponentRepository componentRepository) {
        this.mapper = mapper;
        this.applicationRepository = applicationRepository;
        this.componentRepository = componentRepository;

    }
   
    @Override
    public ApplicationDTO findById(Long id) throws ApplicationException {
        Application searched = this.applicationRepository.findById(id).orElseThrow(() -> new ApplicationException("No se ha encontrado el id: " + id));
        return this.changeToApplicationDTO(searched);
    }

    @Override
    public ApplicationDTO findByName(String name) throws ApplicationException {
        Application searched = this.applicationRepository.findByName(name).orElseThrow(() -> new ApplicationException("No se ha encontrado ninguna aplicación con este nombre: " + name));
        return this.changeToApplicationDTO(searched);
    }

    @Override
    public List<ApplicationDTO> findAllByAppType(AppType appType) throws ApplicationException {
        try {
            logger.info("Buscando aplicaciones por tipo: " + appType);
            List<Application> searched = this.applicationRepository.findAllByAppType(appType);
            return this.changeListToApplicationDTOs(searched);
        } catch (IllegalArgumentException e) {
            throw new ApplicationException("El tipo solo puede ser CaaS o PaaS");
        }
    }

    @Override
    public ApplicationDTO findApplicationByComponentId(Long id) throws ApplicationException {
        Application searched = applicationRepository.findApplicationByComponentId(id).orElseThrow(() -> new ApplicationException("No se ha encontrado ninguna aplicación con este id de componente: " + id));
        return this.changeToApplicationDTO(searched);
     
    }

    @Transactional
    @Override
    public ApplicationDTO createApplication(Application application) throws ApplicationException {
        final Application created = this.applicationRepository.save(application);
        logger.info("Se ha creado una aplicación[{}]", created);
        return this.changeToApplicationDTO(created);
    }

    @Transactional
    @Override
    public ApplicationDTO updateApplication(Application application) throws ApplicationException {
        Application toUpdate = this.applicationRepository.findById(application.getId()).orElseThrow(() -> new ApplicationException("No se ha encontrado el id: " + application.getId()));
        logger.trace("Se va a actualizar la aplicación: " + toUpdate);
        toUpdate.setName(application.getName());
        toUpdate.setAppType(application.getAppType());
        toUpdate.setPlatformType(application.getPlatformType());
        Application updated = this.applicationRepository.save(toUpdate);
        logger.trace("Aplicación guardada: " + updated);
        return this.changeToApplicationDTO(updated);
    }

    @Transactional
    @Override
    public void deleteById(Long id) throws ApplicationException {
        Application toDelete = this.applicationRepository.findById(id).orElseThrow(() -> new ApplicationException("No se ha encontrado el id: " + id));
        applicationRepository.delete(toDelete);
        logger.info("Aplicación borrada");
    }
    
    @Transactional
    public ApplicationDTO updateComponentToApplication(Component component, Long id) throws ApplicationException {
        Application application = this.applicationRepository.findById(id).orElseThrow(() -> new ApplicationException("No se ha encontrado el id: " + id));
        if (application.getComponent().isEmpty()) {
            application.setComponent(new ArrayList<Component>());
            logger.trace("Creada lista de componentes vacia y asignada a aplicación");
        }
        List<Component> compList = application.getComponent();
        compList.add(component);
        application.setComponent(compList);
        logger.info("Actualizada la lista de entornos de la aplicación :" + compList);
        component.setApplication(application);
        logger.info("Asignada aplicación al componente");
        Application appSaved = this.applicationRepository.save(application);
        this.componentRepository.save(component);
        return changeToApplicationDTO(appSaved);
        
    }

    private ApplicationDTO changeToApplicationDTO (Application application) {
        return this.mapper.map(application, ApplicationDTO.class);
    }

    private List<ApplicationDTO> changeListToApplicationDTOs (List<Application> application) {
        List<ApplicationDTO> listDTO = application.stream().map(app -> this.changeToApplicationDTO(app)).collect(Collectors.toList());
        return listDTO;
    }
}
