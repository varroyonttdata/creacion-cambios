package com.nttdata.caixa.gestion.cloud.backend.entities.dto;

import com.nttdata.caixa.gestion.cloud.backend.entities.enums.EnvironmentType;

public class EnvironmentDTO {

    private Long id;
    private EnvironmentType type;
    
    public EnvironmentDTO() {
    }

    public EnvironmentDTO(Long id, EnvironmentType type) {
        this.id = id;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EnvironmentType getType() {
        return type;
    }

    public void setType(EnvironmentType type) {
        this.type = type;
    }

}

