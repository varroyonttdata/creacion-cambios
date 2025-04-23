package com.nttdata.caixa.gestion.cloud.backend.services;


import java.util.List;

import com.nttdata.caixa.gestion.cloud.backend.entities.Application;
import com.nttdata.caixa.gestion.cloud.backend.entities.dto.ApplicationDTO;
import com.nttdata.caixa.gestion.cloud.backend.entities.enums.AppType;
import com.nttdata.caixa.gestion.cloud.backend.exceptions.ApplicationException;

public interface ApplicationService {

    ApplicationDTO createApplication (Application application) throws ApplicationException;

    ApplicationDTO updateApplication (Application application) throws ApplicationException;

    void deleteById(Long id) throws ApplicationException;

    ApplicationDTO findById(Long id) throws ApplicationException;

    ApplicationDTO findByName(String name) throws ApplicationException;

    ApplicationDTO findApplicationByComponentId (Long id) throws ApplicationException;

    List<ApplicationDTO> findAllByAppType(AppType appType) throws ApplicationException;
}
