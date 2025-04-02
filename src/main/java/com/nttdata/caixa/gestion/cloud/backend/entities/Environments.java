package com.nttdata.caixa.gestion.cloud.backend.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


import com.nttdata.caixa.gestion.cloud.backend.entities.enums.Environment;

@Entity
@Table(name = "environments")
public class Environments {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "environments_seq")
    @SequenceGenerator(name = "environments_seq", allocationSize = 1)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Environment environment;

    @ManyToOne()
    @JoinColumn(name = "applications_id")
    private Applications applications;

    public Environments() {
    }

    public Environments(Long id, Environment environment, Applications applications) {
        this.id = id;
        this.environment = environment;
        this.applications = applications;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public Applications getApplications() {
        return applications;
    }

    public void setApplications(Applications applications) {
        this.applications = applications;
    }

    


    
}
