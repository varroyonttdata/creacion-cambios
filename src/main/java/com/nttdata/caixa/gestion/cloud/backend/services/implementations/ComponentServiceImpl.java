package com.nttdata.caixa.gestion.cloud.backend.services.implementations;

import org.springframework.stereotype.Service;

import com.nttdata.caixa.gestion.cloud.backend.entities.Component;
import com.nttdata.caixa.gestion.cloud.backend.entities.enums.ComponentType;
import com.nttdata.caixa.gestion.cloud.backend.services.ComponentServices;

@Service
public class ComponentServiceImpl implements ComponentServices {

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

}
