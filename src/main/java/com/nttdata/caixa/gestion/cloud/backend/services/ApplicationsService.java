package com.nttdata.caixa.gestion.cloud.backend.services;


import com.nttdata.caixa.gestion.cloud.backend.entities.Applications;

import com.nttdata.caixa.gestion.cloud.backend.exceptions.ApplicationsException;

public interface ApplicationsService {

    Applications findById(Long id) throws ApplicationsException;

    Applications findByName(String name) throws ApplicationsException;

    Applications findApplicationsByEnvironmentsId (Long id) throws ApplicationsException;
}
