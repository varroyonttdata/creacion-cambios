package com.nttdata.caixa.gestion.cloud.backend.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.nttdata.caixa.gestion.cloud.backend.entities.enums.EnvironmentType;

@Entity
@Table(name = "environments")
public class Environment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "environments_seq")
    @SequenceGenerator(name = "environments_seq", allocationSize = 1)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NotNull
    private EnvironmentType environmentType;

    @OneToMany(mappedBy = "environment")
    private List<ComponentEnvironment> componentEnvironments;

    public Environment() {
    }

    public Environment(@NotNull EnvironmentType environmentType) {
        this.environmentType = environmentType;
    }

    public Environment(Long id, EnvironmentType environmentType) {
        this.id = id;
        this.environmentType = environmentType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EnvironmentType getEnvironmentType() {
        return environmentType;
    }

    public void setEnvironmentType(EnvironmentType environmentType) {
        this.environmentType = environmentType;
    }

    public List<ComponentEnvironment> getComponentEnvironments() {
        return componentEnvironments;
    }

    public void setComponentEnvironments(List<ComponentEnvironment> componentEnvironments) {
        this.componentEnvironments = componentEnvironments;
    } 
    
}
