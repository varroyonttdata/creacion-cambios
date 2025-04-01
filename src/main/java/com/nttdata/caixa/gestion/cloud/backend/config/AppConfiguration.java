package com.nttdata.caixa.gestion.cloud.backend.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nttdata.caixa.gestion.cloud.backend.repositories.ApplicationsRepository;
import com.nttdata.caixa.gestion.cloud.backend.repositories.ComponentRepository;
import com.nttdata.caixa.gestion.cloud.backend.repositories.EnvironmentsRepository;
import com.nttdata.caixa.gestion.cloud.backend.services.ApplicationsService;
import com.nttdata.caixa.gestion.cloud.backend.services.ComponentServices;
import com.nttdata.caixa.gestion.cloud.backend.services.EnvironmentsService;
import com.nttdata.caixa.gestion.cloud.backend.services.implementations.ApplicationsServiceImpl;
import com.nttdata.caixa.gestion.cloud.backend.services.implementations.ComponentServiceImpl;
import com.nttdata.caixa.gestion.cloud.backend.services.implementations.EnvironmentsServiceImpl;

@Configuration
public class AppConfiguration {

    @Bean
    public ApplicationsServiceImpl applicationsServiceImpl(ApplicationsRepository applicationsRepository, ModelMapper mapper) {
        return new ApplicationsServiceImpl(mapper, applicationsRepository);
    }

    @Bean
    public EnvironmentsServiceImpl environmentsServiceImpl(EnvironmentsRepository environmentsRepository, ApplicationsServiceImpl applicationsServiceImpl, ModelMapper mapper) {
        return new EnvironmentsServiceImpl(environmentsRepository, applicationsServiceImpl, mapper);
    }

    @Bean
    public ComponentServiceImpl componentServicesImpl(ComponentRepository componentRepository, ModelMapper mapper) {
        return new ComponentServiceImpl(componentRepository, mapper);
    }

    @Bean
    public ModelMapper modelMapper() {
        final ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }
}
