package com.nttdata.caixa.gestion.cloud.backend.services.implementations;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;

import com.nttdata.caixa.gestion.cloud.backend.entities.Applications;
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
    public Applications findById(Long id) throws ApplicationsException {
        return this.applicationsRepository.findById(id).orElseThrow(() -> new ApplicationsException("No se ha encontrado el id: " + id));
    }

    @Override
    public Applications findByName(String name) throws ApplicationsException {
        return this.applicationsRepository.findByName(name).orElseThrow(() -> new ApplicationsException("No se ha encontrado ninguna aplicación con este nombre: " + name));
    }

    //La llamada tiene que venir con el formato adecuado Primera Mayuscula y Ultima mayuscula.
    public List<Applications> findAllByType(String type) throws ApplicationsException {
        try {
            logger.info("Buscando aplicaciones por tipo: " + type);
            Type enumType = Type.valueOf(type);
            List<Applications> searched = this.applicationsRepository.findAllByType(enumType);
            return searched;
        } catch (IllegalArgumentException e) {
            throw new ApplicationsException("El tipo solo puede ser CaaS o PaaS");
        }
    }

    @Override
    public Applications findApplicationsByEnvironmentsId(Long id) throws ApplicationsException {
        final Applications searched = applicationsRepository.findApplicationsByEnvironmentsId(id).orElseThrow(() -> new ApplicationsException("No se ha encontrado ninguna aplicación con este id de entorno: " + id));
        return searched;
     
    }


    @Override
    public Applications createApplications(Applications applications) throws ApplicationsException {
        final Applications created = this.applicationsRepository.save(applications);
        logger.info("Se ha creado una aplicación[{}]", created);
        return created;
    }


    @Override
    public Applications updateApplications(Applications applications) throws ApplicationsException {
        Applications toUpdate = this.findById(applications.getId());
        logger.trace("Se va a actualizar la aplicación: " + toUpdate);
        toUpdate.setName(applications.getName());
        toUpdate.setType(applications.getType());
        Applications updated = this.applicationsRepository.save(toUpdate);
        logger.trace("Aplicación guardada: " + updated);
        return updated;
    }


    @Override
    public void deleteById(Long id) throws ApplicationsException {
        Applications toDelete = this.findById(id);
        applicationsRepository.delete(toDelete);
        logger.info("Aplicación borrada");
    }
}
