package com.nttdata.caixa.gestion.cloud.backend.services.implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;

import com.nttdata.caixa.gestion.cloud.backend.entities.Applications;
import com.nttdata.caixa.gestion.cloud.backend.entities.dto.ApplicationsDTO;
import com.nttdata.caixa.gestion.cloud.backend.entities.enums.Type;
import com.nttdata.caixa.gestion.cloud.backend.exceptions.ApplicationsException;
import com.nttdata.caixa.gestion.cloud.backend.repositories.ApplicationsRepository;
import com.nttdata.caixa.gestion.cloud.backend.services.ApplicationsService;



public class ApplicationsServiceImpl implements ApplicationsService {

    private static final Logger logger = LogManager.getLogger(ApplicationsServiceImpl.class);

    private ModelMapper mapper;
    private ApplicationsRepository applicationsRepository;

    public ApplicationsServiceImpl(ModelMapper mapper, ApplicationsRepository applicationsRepository) {
        this.mapper = mapper;
        this.applicationsRepository = applicationsRepository;
    }
   
    @Override
    public ApplicationsDTO findById(Long id) throws ApplicationsException {
        Applications searched = this.applicationsRepository.findById(id).orElseThrow(() -> new ApplicationsException("No se ha encontrado el id: " + id));
        return this.changeToApplicationsDTO(searched);
    }

    @Override
    public ApplicationsDTO findByName(String name) throws ApplicationsException {
        Applications searched = this.applicationsRepository.findByName(name).orElseThrow(() -> new ApplicationsException("No se ha encontrado ninguna aplicación con este nombre: " + name));
        return this.changeToApplicationsDTO(searched);
    }

    //La llamada tiene que venir con el formato adecuado Primera Mayuscula y Ultima mayuscula.
    public List<ApplicationsDTO> findAllByType(String type) throws ApplicationsException {
        try {
            logger.info("Buscando aplicaciones por tipo: " + type);
            Type enumType = Type.valueOf(type);
            List<Applications> searched = this.applicationsRepository.findAllByType(enumType);
            return this.changeListToApplicationsDTOs(searched);
        } catch (IllegalArgumentException e) {
            throw new ApplicationsException("El tipo solo puede ser CaaS o PaaS");
        }
    }

    @Override
    public ApplicationsDTO findApplicationsByEnvironmentsId(Long id) throws ApplicationsException {
        Applications searched = applicationsRepository.findApplicationsByEnvironmentsId(id).orElseThrow(() -> new ApplicationsException("No se ha encontrado ninguna aplicación con este id de entorno: " + id));
        return this.changeToApplicationsDTO(searched);
     
    }


    @Override
    public ApplicationsDTO createApplications(Applications applications) throws ApplicationsException {
        final Applications created = this.applicationsRepository.save(applications);
        logger.info("Se ha creado una aplicación[{}]", created);
        return this.changeToApplicationsDTO(created);
    }


    @Override
    public ApplicationsDTO updateApplications(Applications applications) throws ApplicationsException {
        Applications toUpdate = this.applicationsRepository.findById(applications.getId()).orElseThrow(() -> new ApplicationsException("No se ha encontrado el id: " + applications.getId()));
        logger.trace("Se va a actualizar la aplicación: " + toUpdate);
        toUpdate.setName(applications.getName());
        toUpdate.setType(applications.getType());
        Applications updated = this.applicationsRepository.save(toUpdate);
        logger.trace("Aplicación guardada: " + updated);
        return this.changeToApplicationsDTO(updated);
    }


    @Override
    public void deleteById(Long id) throws ApplicationsException {
        Applications toDelete = this.applicationsRepository.findById(id).orElseThrow(() -> new ApplicationsException("No se ha encontrado el id: " + id));
        applicationsRepository.delete(toDelete);
        logger.info("Aplicación borrada");
    }

    private ApplicationsDTO changeToApplicationsDTO (Applications applications) {
        return this.mapper.map(applications, ApplicationsDTO.class);
    }

    private List<ApplicationsDTO> changeListToApplicationsDTOs (List<Applications> applications) {
        List<ApplicationsDTO> listDTO = applications.stream().map(app -> this.changeToApplicationsDTO(app)).collect(Collectors.toList());
        return listDTO;
    }
}
