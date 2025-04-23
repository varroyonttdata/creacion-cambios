package com.nttdata.caixa.gestion.cloud.backend.entities.dto;

import com.nttdata.caixa.gestion.cloud.backend.entities.enums.ComponentType;

public class ComponentDTO {

    private Long id;
    private String name;
    private ComponentType componentType;

    
    public ComponentDTO() {
    }

    public ComponentDTO(Long id, String name, ComponentType componentType) {
        this.id = id;
        this.name = name;
        this.componentType = componentType;
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

    public ComponentType getComponentType() {
        return componentType;
    }

    public void setComponentType(ComponentType componentType) {
        this.componentType = componentType;
    }
    

    
}
