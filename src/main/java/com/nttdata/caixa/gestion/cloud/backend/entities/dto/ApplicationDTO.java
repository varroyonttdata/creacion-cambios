package com.nttdata.caixa.gestion.cloud.backend.entities.dto;

import com.nttdata.caixa.gestion.cloud.backend.entities.enums.AppType;
import com.nttdata.caixa.gestion.cloud.backend.entities.enums.PlatformType;

public class ApplicationDTO {

    private Long id;
    private String name;
    private AppType appType;
    private PlatformType platformType;

    public ApplicationDTO() {
    }

    public ApplicationDTO(Long id, String name, AppType appType, PlatformType platformType) {
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

    public PlatformType getPlatformType() {
        return platformType;
    }
    public void setPlatformType(PlatformType platformType) {
        this.platformType = platformType;
    }

    public AppType getAppType() {
        return appType;
    }

    public void setAppType(AppType appType) {
        this.appType = appType;
    }    
}
