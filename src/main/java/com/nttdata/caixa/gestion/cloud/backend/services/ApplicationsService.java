package com.nttdata.caixa.gestion.cloud.backend.services;


import com.nttdata.caixa.gestion.cloud.backend.entities.Applications;
import com.nttdata.caixa.gestion.cloud.backend.entities.dto.ApplicationsDTO;
import com.nttdata.caixa.gestion.cloud.backend.exceptions.ApplicationsException;

public interface ApplicationsService {

    ApplicationsDTO createApplications (Applications applications) throws ApplicationsException;

    ApplicationsDTO updateApplications (Applications applications) throws ApplicationsException;

    void deleteById(Long id) throws ApplicationsException;

    ApplicationsDTO findById(Long id) throws ApplicationsException;

    ApplicationsDTO findByName(String name) throws ApplicationsException;

    ApplicationsDTO findApplicationsByEnvironmentsId (Long id) throws ApplicationsException;
}
