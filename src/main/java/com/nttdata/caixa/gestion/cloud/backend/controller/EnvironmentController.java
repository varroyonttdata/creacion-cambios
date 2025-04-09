package com.nttdata.caixa.gestion.cloud.backend.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.caixa.gestion.cloud.backend.entities.Environment;
import com.nttdata.caixa.gestion.cloud.backend.entities.dto.EnvironmentDTO;
import com.nttdata.caixa.gestion.cloud.backend.exceptions.ApplicationException;
import com.nttdata.caixa.gestion.cloud.backend.exceptions.EnvironmentException;
import com.nttdata.caixa.gestion.cloud.backend.services.implementations.EnvironmentServiceImpl;

@RestController
@RequestMapping("/environment")
public class EnvironmentController {

    private static final Logger logger = LogManager.getLogger(Environment.class);

    private final EnvironmentServiceImpl environmentServiceImpl;

    public EnvironmentController(EnvironmentServiceImpl environmentServiceImpl) {
        this.environmentServiceImpl = environmentServiceImpl;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEnvironmentsById(@PathVariable Long id) throws EnvironmentException {
        EnvironmentDTO toDelete = environmentServiceImpl.findById(id);
        logger.info("Entorno a borrar: " + toDelete);
        environmentServiceImpl.deleteEnvironmentById(id);
        return ResponseEntity.ok("Entorno borrado: " + id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnvironmentDTO> findById(@PathVariable Long id) throws EnvironmentException{
        logger.trace("Se busca entorno con id: ", id);
        return ResponseEntity.ok(environmentServiceImpl.findById(id));
    }

    @ExceptionHandler({EnvironmentException.class, ApplicationException.class})
    public ResponseEntity<?> handlerEnvironmentsException(EnvironmentException environmentsException) {
        return new ResponseEntity<>(environmentsException.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
