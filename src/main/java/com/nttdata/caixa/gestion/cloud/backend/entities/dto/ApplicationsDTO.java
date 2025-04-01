package com.nttdata.caixa.gestion.cloud.backend.entities.dto;

import com.nttdata.caixa.gestion.cloud.backend.entities.enums.Type;

public class ApplicationsDTO {

    private Long id;
    private String name;
    private Type type;

    public ApplicationsDTO() {
    }

    public ApplicationsDTO(Long id, String name, Type type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    

    
}
