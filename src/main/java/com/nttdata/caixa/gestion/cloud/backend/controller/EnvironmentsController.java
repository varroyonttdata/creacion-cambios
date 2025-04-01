package com.nttdata.caixa.gestion.cloud.backend.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.caixa.gestion.cloud.backend.entities.Environments;
import com.nttdata.caixa.gestion.cloud.backend.entities.dto.EnvironmentsDTO;
import com.nttdata.caixa.gestion.cloud.backend.exceptions.ApplicationsException;
import com.nttdata.caixa.gestion.cloud.backend.exceptions.EnvironmentsException;
import com.nttdata.caixa.gestion.cloud.backend.services.implementations.EnvironmentsServiceImpl;

@RestController
@RequestMapping("/environments")
public class EnvironmentsController {

    private static final Logger logger = LogManager.getLogger(Environments.class);

    private final EnvironmentsServiceImpl environmentsServiceImpl;

    public EnvironmentsController(EnvironmentsServiceImpl environmentsServiceImpl) {
        this.environmentsServiceImpl = environmentsServiceImpl;
    }

    @PostMapping("/create")
    public ResponseEntity<EnvironmentsDTO> createEnvironments(@RequestBody Environments environments) throws EnvironmentsException {
        EnvironmentsDTO saved = environmentsServiceImpl.createEnvironments(environments);
        logger.info("Entorno creado: " + saved);
        return ResponseEntity.ok(saved);
    }

    //TODO Metodo createEnvironments no agrega dependencia application_id a los entornos creados, ver que pasa, update seguramente tp funcione.
    // @PostMapping("/create/{id}")
    // public ResponseEntity<Environments> createEnvironments(@PathVariable Long id, @RequestBody Environments environments) throws EnvironmentsException, ApplicationsException {
    //     Environments saved = environmentsServiceImpl.createtest(environments, id);
    //     logger.info("Entorno creado: " + saved);
    //     return ResponseEntity.ok(saved);
    // }

    @PutMapping("/update")
    public ResponseEntity<EnvironmentsDTO> updateEnvironments(@RequestBody Environments environments) throws EnvironmentsException {
        EnvironmentsDTO updated = environmentsServiceImpl.updateEnvironments(environments);
        logger.info("Entorno actualizado: " + updated);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEnvironmentsById(@PathVariable Long id) throws EnvironmentsException {
        EnvironmentsDTO toDelete = environmentsServiceImpl.findById(id);
        logger.info("Entorno a borrar: " + toDelete);
        environmentsServiceImpl.deleteEnvironmentsById(id);
        return ResponseEntity.ok("Entorno borrado: " + id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnvironmentsDTO> findById(@PathVariable Long id) throws EnvironmentsException{
        logger.trace("Se busca entorno con id: ", id);
        return ResponseEntity.ok(environmentsServiceImpl.findById(id));
    }

    @GetMapping("/environment/{environment}")
    public ResponseEntity<List<EnvironmentsDTO>> findAllByEnvironment(@PathVariable String environment) throws EnvironmentsException {
        logger.trace("Se buscan todos los entornos por entorno.");
        return ResponseEntity.ok(environmentsServiceImpl.findAllByEnvironment(environment));
    }


    @ExceptionHandler({EnvironmentsException.class})
    public ResponseEntity<?> handlerEnvironmentsException(EnvironmentsException environmentsException) {
        return new ResponseEntity<>(environmentsException.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
