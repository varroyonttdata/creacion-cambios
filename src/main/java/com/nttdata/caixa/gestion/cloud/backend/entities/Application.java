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
import javax.validation.constraints.Size;

import com.nttdata.caixa.gestion.cloud.backend.entities.enums.AppType;
import com.nttdata.caixa.gestion.cloud.backend.entities.enums.PlatformType;

@Entity
@Table(name = "application")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "application_seq")
    @SequenceGenerator(name = "application_seq", allocationSize = 1)
    private Long id;
    
    @Size(max = 6, min = 6)
    private String name;

    @Enumerated(EnumType.STRING)
    @NotNull
    private AppType appType;

    @Enumerated(EnumType.STRING)
    @NotNull
    private PlatformType platformType;

    @OneToMany(mappedBy = "application")
    private List<Component> component;

    public Application() {
    }

    public Application(Long id, String name, AppType appType, PlatformType platformType) {
        this.id = id;
        this.name = name;
        this.appType = appType;
        this.platformType = platformType;
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

    public AppType getAppType() {
        return appType;
    }

    public void setAppType(AppType appType) {
        this.appType = appType;
    }

    public PlatformType getPlatformType() {
        return platformType;
    }

    public void setPlatformType(PlatformType platformType) {
        this.platformType = platformType;
    }

    public List<Component> getComponent() {
        return component;
    }

    public void setComponent(List<Component> component) {
        this.component = component;
    }

    @Override
    public String toString() {
        return "Application [id=" + id + ", name=" + name + ", appType=" + appType + ", platformType=" + platformType
                + "]";
    }

    
}
