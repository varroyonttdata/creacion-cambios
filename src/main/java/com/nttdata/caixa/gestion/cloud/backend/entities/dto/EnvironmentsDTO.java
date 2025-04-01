package com.nttdata.caixa.gestion.cloud.backend.entities.dto;

import com.nttdata.caixa.gestion.cloud.backend.entities.enums.Environment;

public class EnvironmentsDTO {

    private Long id;
    private Environment environment;
    private Long application_id;


    public EnvironmentsDTO() {
    }

    public EnvironmentsDTO(Long id, Environment environment, Long application_id) {
        this.id = id;
        this.environment = environment;
        this.application_id = application_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public Long getApplication_id() {
        return application_id;
    }

    public void setApplication_id(Long application_id) {
        this.application_id = application_id;
    }

    
    
}

