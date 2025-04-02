package com.nttdata.caixa.gestion.cloud.backend.services.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.nttdata.caixa.gestion.cloud.backend.entities.Component;
import com.nttdata.caixa.gestion.cloud.backend.entities.enums.ComponentType;
import com.nttdata.caixa.gestion.cloud.backend.repositories.ComponentRepository;
import com.nttdata.caixa.gestion.cloud.backend.services.ComponentServices;


@SuppressWarnings("unused")
public class ComponentServiceImpl implements ComponentServices {

    private final ComponentRepository componentRepository;
    private ModelMapper mapper;

    public ComponentServiceImpl(ComponentRepository componentRepository, ModelMapper mapper) {
        this.componentRepository = componentRepository;
        this.mapper = mapper;
    }

    @Override
    public Component findById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public Component findByName(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByName'");
    }

    @Override
    public Component findByComponentType(ComponentType componentType) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByComponentType'");
    }

    @Override
    public Component createComponent(Component component) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createComponent'");
    }

    @Override
    public Component updateComponent(Component component) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateComponent'");
    }

    @Override
    public void deleteComponentById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteComponentById'");
    }

}
