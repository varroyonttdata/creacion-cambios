package com.nttdata.caixa.gestion.cloud.backend.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nttdata.caixa.gestion.cloud.backend.entities.Environments;
import com.nttdata.caixa.gestion.cloud.backend.entities.dto.EnvironmentsDTO;
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
    public ApplicationsServiceImpl applicationsServiceImpl(ApplicationsRepository applicationsRepository, ModelMapper mapper, EnvironmentsRepository environmentsRepository) {
        return new ApplicationsServiceImpl(mapper, applicationsRepository, environmentsRepository);
    }

    @Bean
    public EnvironmentsServiceImpl environmentsServiceImpl(EnvironmentsRepository environmentsRepository, ModelMapper mapper, ApplicationsServiceImpl applicationsServiceImpl, ApplicationsRepository applicationsRepository) {
        return new EnvironmentsServiceImpl(environmentsRepository, mapper, applicationsServiceImpl, applicationsRepository);
    }

    @Bean
    public ComponentServiceImpl componentServicesImpl(ComponentRepository componentRepository, ModelMapper mapper) {
        return new ComponentServiceImpl(componentRepository, mapper);
    }

    @Bean
    public ModelMapper modelMapper() {
         ModelMapper modelMapper = new ModelMapper();
         TypeMap<Environments, EnvironmentsDTO> propertyMapper = modelMapper.createTypeMap(Environments.class, EnvironmentsDTO.class);
         propertyMapper.addMappings(
                 mapper -> mapper.map(src -> src.getApplications().getId(), EnvironmentsDTO::setApplications_id));
        return modelMapper;
    }
}
