package com.nttdata.caixa.gestion.cloud.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.caixa.gestion.cloud.backend.entities.Environments;
import com.nttdata.caixa.gestion.cloud.backend.exceptions.EnvironmentsException;
import com.nttdata.caixa.gestion.cloud.backend.services.implementations.EnvironmentsServiceImpl;

@RestController
@RequestMapping("/environments")
public class EnvironmentsController {

    private final EnvironmentsServiceImpl environmentsServiceImpl;

    public EnvironmentsController(EnvironmentsServiceImpl environmentsServiceImpl) {
        this.environmentsServiceImpl = environmentsServiceImpl;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Environments> findById(@PathVariable Long id) throws EnvironmentsException{
        return ResponseEntity.ok(environmentsServiceImpl.findById(id));
    }

    @GetMapping("/environment/{environment}")
    public ResponseEntity<List<Environments>> findAllByEnvironment(@PathVariable String environment) throws EnvironmentsException {
        return ResponseEntity.ok(environmentsServiceImpl.findAllByEnvironment(environment));
    }


    @ExceptionHandler({EnvironmentsException.class})
    public ResponseEntity<?> handlerEnvironmentsException(EnvironmentsException environmentsException) {
        return new ResponseEntity<>(environmentsException.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
