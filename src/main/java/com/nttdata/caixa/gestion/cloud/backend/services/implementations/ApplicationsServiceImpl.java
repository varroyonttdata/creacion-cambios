package com.nttdata.caixa.gestion.cloud.backend.services.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nttdata.caixa.gestion.cloud.backend.entities.Applications;
import com.nttdata.caixa.gestion.cloud.backend.entities.enums.Type;
import com.nttdata.caixa.gestion.cloud.backend.exceptions.ApplicationsException;
import com.nttdata.caixa.gestion.cloud.backend.repositories.ApplicationsRepository;
import com.nttdata.caixa.gestion.cloud.backend.services.ApplicationsService;


@Service
public class ApplicationsServiceImpl implements ApplicationsService {

    private final ApplicationsRepository applicationsRepository;


    public ApplicationsServiceImpl(ApplicationsRepository applicationsRepository) {
        this.applicationsRepository = applicationsRepository;
    }

   
    //Revisar cuando actualice BBDD

    @Override
    public Applications findById(Long id) throws ApplicationsException {
        return this.applicationsRepository.findById(id).orElseThrow(() -> new ApplicationsException("No se ha encontrado el id: " + id));
    }

    @Override
    public Applications findByName(String name) throws ApplicationsException {
        return this.applicationsRepository.findByName(name).orElseThrow(() -> new ApplicationsException("No se ha encontrado ninguna aplicación con este nombre: " + name));
    }


    public List<Applications> findAllByType(String type) throws ApplicationsException {
        try {
            Type enumType = Type.valueOf(type);
            List<Applications> searched = this.applicationsRepository.findAllByType(enumType);
            return searched;
        } catch (IllegalArgumentException e) {
            throw new ApplicationsException("El tipo solo puede ser CaaS o PaaS");
        }
    }

    @Override
    public Applications findApplicationsByEnvironmentsId(Long id) throws ApplicationsException {
        Applications searched = applicationsRepository.findApplicationsByEnvironmentsId(id).orElseThrow(() -> new ApplicationsException("No se ha encontrado ninguna aplicación con este id de entorno: " + id));
        return searched;
     
    }
}
