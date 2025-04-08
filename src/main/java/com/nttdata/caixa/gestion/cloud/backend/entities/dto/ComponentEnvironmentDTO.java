package com.nttdata.caixa.gestion.cloud.backend.entities.dto;

public class ComponentEnvironmentDTO {

    private Long id;
    private Integer replica;
    
    public ComponentEnvironmentDTO() {
    }

    public ComponentEnvironmentDTO(Long id, Integer replica) {
        this.id = id;
        this.replica = replica;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getReplica() {
        return replica;
    }

    public void setReplica(Integer replica) {
        this.replica = replica;
    }


    
}
