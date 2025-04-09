package com.nttdata.caixa.gestion.cloud.backend.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nttdata.caixa.gestion.cloud.backend.aspect.ApplicationsAspect;
import com.nttdata.caixa.gestion.cloud.backend.repositories.ApplicationRepository;
import com.nttdata.caixa.gestion.cloud.backend.repositories.ComponentEnvironmentRepository;
import com.nttdata.caixa.gestion.cloud.backend.repositories.ComponentRepository;
import com.nttdata.caixa.gestion.cloud.backend.repositories.EnvironmentRepository;
import com.nttdata.caixa.gestion.cloud.backend.services.ComponentEnvironmentService;
import com.nttdata.caixa.gestion.cloud.backend.services.implementations.ApplicationServiceImpl;
import com.nttdata.caixa.gestion.cloud.backend.services.implementations.ComponentEnvironmentServiceImpl;
import com.nttdata.caixa.gestion.cloud.backend.services.implementations.ComponentServiceImpl;
import com.nttdata.caixa.gestion.cloud.backend.services.implementations.EnvironmentServiceImpl;

@Configuration
public class AppConfiguration {

    @Bean
    public ApplicationsAspect applicationsAspect() {
        return new ApplicationsAspect();
    }

    @Bean
    public ApplicationServiceImpl applicationsServiceImpl(ApplicationRepository applicationsRepository, ModelMapper mapper, ComponentRepository componentRepository) {
        return new ApplicationServiceImpl(mapper, applicationsRepository, componentRepository);
    }

    @Bean
    public EnvironmentServiceImpl environmentsServiceImpl(EnvironmentRepository environmentRepository, ModelMapper mapper) {
        return new EnvironmentServiceImpl(environmentRepository, mapper);
    }

    @Bean
    public ComponentServiceImpl componentServicesImpl(ComponentRepository componentRepository, ModelMapper mapper) {
        return new ComponentServiceImpl(componentRepository, mapper);
    }

    @Bean
    public ModelMapper modelMapper() {
         ModelMapper modelMapper = new ModelMapper();
        //  TypeMap<Environment, EnvironmentsDTO> propertyMapper = modelMapper.createTypeMap(Environment.class, EnvironmentsDTO.class);
        //  propertyMapper.addMappings(
        //          mapper -> mapper.map(src -> src.getApplications().getId(), EnvironmentsDTO::setApplications_id));
        return modelMapper;
    }

    @Bean
    public AfterLoadTasks afterLoadTasks(EnvironmentServiceImpl environmentService) {
        return new AfterLoadTasks(environmentService);
    }

    @Bean
    public ComponentEnvironmentService componentEnvironmentService(ComponentRepository componentRepository, EnvironmentRepository environmentRepository, ComponentEnvironmentRepository componentEnvironmentRepository, ModelMapper mapper) {
        return new ComponentEnvironmentServiceImpl(componentRepository, environmentRepository, componentEnvironmentRepository, mapper);
    }
}
