package com.nttdata.caixa.gestion.cloud.backend.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "component_environment")
public class ComponentEnvironment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "component_environment_id_seq")
    @SequenceGenerator(name = "component_environment_id_seq", allocationSize = 1)
    private Long id;
    
    @NotNull
    private Integer replica;

    @ManyToOne
    @JoinColumn(name = "component_id")
    private Component component;

    @ManyToOne
    @JoinColumn(name = "environment_id")
    private Environment environment;

    public ComponentEnvironment() {
    }

    public ComponentEnvironment(@NotNull Integer replica) {
        this.replica = replica;
    }

    public ComponentEnvironment(Long id, Integer replica) {
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

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public String toString() {
        return "ComponentEnvironment [id=" + id + ", replica=" + replica + "]";
    }

    

}
