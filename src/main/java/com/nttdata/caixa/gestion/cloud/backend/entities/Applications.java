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

import com.nttdata.caixa.gestion.cloud.backend.entities.enums.Type;

@Entity
@Table(name = "applications")
public class Applications {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "applications_seq")
    @SequenceGenerator(name = "applications_seq", allocationSize = 1)
    private Long id;
    private String name;

    @Enumerated(EnumType.STRING)
    private Type type;

    @OneToMany(mappedBy = "applications")
    private List<Environments> environments;

    public Applications() {
    }

    public Applications(Long id, String name, Type type) {
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
