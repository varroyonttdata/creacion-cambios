package com.nttdata.caixa.gestion.cloud.backend.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.nttdata.caixa.gestion.cloud.backend.entities.enums.ComponentType;

@Entity
@Table(name = "component")
public class Component {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq")
    @SequenceGenerator(name = "id_seq", allocationSize = 1)
    private Long id;
    
    @NotBlank
    private String name;

    @Enumerated(EnumType.STRING)
    @NotNull
    private ComponentType componentType;

    @ManyToOne
    @JoinColumn(name = "application_id")
    private Application application;

    @OneToMany(mappedBy = "component")
    private List<ComponentEnvironment> componentEnvironments;

    public Component() {
    }
    
    public Component(Long id, String name, ComponentType componentType) {
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

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public List<ComponentEnvironment> getComponentEnvironments() {
        return componentEnvironments;
    }

    public void setComponentEnvironments(List<ComponentEnvironment> componentEnvironments) {
        this.componentEnvironments = componentEnvironments;
    }

    @Override
    public String toString() {
        return "Component [id=" + id + ", name=" + name + ", componentType=" + componentType + ", application="
                + application + ", componentEnvironments=" + componentEnvironments + "]";
    }
    
    
}
