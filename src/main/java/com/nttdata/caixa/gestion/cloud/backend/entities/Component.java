package com.nttdata.caixa.gestion.cloud.backend.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.nttdata.caixa.gestion.cloud.backend.entities.enums.ComponentType;

@Entity
@Table(name = "component")
public class Component {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq")
    @SequenceGenerator(name = "id_seq", allocationSize = 1)
    private Long id;
    
    private String name;
    private String replica;
    private Long componentId;

    @Enumerated(EnumType.STRING)
    private ComponentType componentType;

    public Component() {
    }

    public Component(Long id, String name, String replica, Long componentId, ComponentType componentType) {
        this.id = id;
        this.name = name;
        this.replica = replica;
        this.componentId = componentId;
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

    public String getReplica() {
        return replica;
    }

    public void setReplica(String replica) {
        this.replica = replica;
    }

    public Long getComponentId() {
        return componentId;
    }

    public void setComponentId(Long componentId) {
        this.componentId = componentId;
    }

    public ComponentType getComponentType() {
        return componentType;
    }

    public void setComponentType(ComponentType componentType) {
        this.componentType = componentType;
    }
    
}
