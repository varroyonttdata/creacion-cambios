package com.nttdata.caixa.gestion.cloud.backend.entities.dto;

import com.nttdata.caixa.gestion.cloud.backend.entities.enums.Environment;

public class EnvironmentsDTO {

    private Long id;
    private Environment environment;
    private Long applications_id;


    public EnvironmentsDTO() {
    }

    public EnvironmentsDTO(Long id, Environment environment, Long applications_id) {
        this.id = id;
        this.environment = environment;
        this.applications_id = applications_id;
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

    public Long getApplications_id() {
        return applications_id;
    }

    public void setApplications_id(Long applications_id) {
        this.applications_id = applications_id;
    }

    
    
}

