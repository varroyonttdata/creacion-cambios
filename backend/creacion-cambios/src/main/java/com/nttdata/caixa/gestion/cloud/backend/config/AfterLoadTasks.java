package com.nttdata.caixa.gestion.cloud.backend.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

import com.nttdata.caixa.gestion.cloud.backend.entities.Environment;
import com.nttdata.caixa.gestion.cloud.backend.entities.enums.EnvironmentType;
import com.nttdata.caixa.gestion.cloud.backend.services.EnvironmentService;

public class AfterLoadTasks implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger logger = LogManager.getLogger(AfterLoadTasks.class);
    private EnvironmentService environmentService;

    public AfterLoadTasks(EnvironmentService environmentService) {
        this.environmentService = environmentService;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        loadDefaultEnvironments();
    }
    
    private void loadDefaultEnvironments() {
        logger.debug("Comprobando si existen los entornos por defecto...");

        for (EnvironmentType environmentType : EnvironmentType.values()) {

        this.environmentService.findByEnvironmentType(environmentType).orElseGet(() -> {
                final Environment created = this.environmentService.createEnvironment(new Environment(environmentType));
                logger.debug("Se ha creado el entorno [{}]", created);
                return created;
            });
        }
    }
}
