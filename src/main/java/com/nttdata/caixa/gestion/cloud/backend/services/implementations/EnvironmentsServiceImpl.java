package com.nttdata.caixa.gestion.cloud.backend.services.implementations;

import com.nttdata.caixa.gestion.cloud.backend.entities.Environments;
import com.nttdata.caixa.gestion.cloud.backend.entities.enums.Environment;
import com.nttdata.caixa.gestion.cloud.backend.exceptions.EnvironmentsException;
import com.nttdata.caixa.gestion.cloud.backend.repositories.EnvironmentsRepository;
import com.nttdata.caixa.gestion.cloud.backend.services.EnvironmentsService;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class EnvironmentsServiceImpl implements EnvironmentsService {

    private final EnvironmentsRepository environmentsRepository;

    EnvironmentsServiceImpl(EnvironmentsRepository environmentsRepository) {
        this.environmentsRepository = environmentsRepository;
    }

    @Override
    public Environments findById(Long id) throws EnvironmentsException {
        return environmentsRepository.findById(id).orElseThrow(() -> new EnvironmentsException("El entorno no existe"));
    }

    public List<Environments> findAllByEnvironment(String environment) throws EnvironmentsException {
        try { 
            environment = environment.toUpperCase();
            Environment enumEnvironment = Environment.valueOf(environment);
            List<Environments> searched = this.environmentsRepository.findAllByEnvironment(enumEnvironment);
            return searched;
        } catch (IllegalArgumentException e) {
            throw new EnvironmentsException("El entorno no existe");
        }
        
    }

}
