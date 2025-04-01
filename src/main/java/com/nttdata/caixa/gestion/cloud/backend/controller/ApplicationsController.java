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

import com.nttdata.caixa.gestion.cloud.backend.entities.Applications;
import com.nttdata.caixa.gestion.cloud.backend.exceptions.ApplicationsException;
import com.nttdata.caixa.gestion.cloud.backend.services.implementations.ApplicationsServiceImpl;

@RestController
@RequestMapping("/applications")
public class ApplicationsController {

    private static final Logger logger = LogManager.getLogger(ApplicationsController.class);

    private ApplicationsServiceImpl applicationsServiceImpl;

    public ApplicationsController(ApplicationsServiceImpl applicationsServiceImpl) {
        this.applicationsServiceImpl = applicationsServiceImpl;
    }


    @PostMapping("/create")
    public ResponseEntity<Applications> createApplications (@RequestBody Applications applications) throws ApplicationsException{
        logger.info("Aplicación creada: " + applications);
        return ResponseEntity.ok(applicationsServiceImpl.createApplications(applications));
    }

    @PutMapping("/update")
    public ResponseEntity<Applications> updateApplications (@RequestBody Applications applications) throws ApplicationsException{
        Applications updated = applicationsServiceImpl.updateApplications(applications);
        logger.info("Aplicación modificada: " + updated);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteApplications (@PathVariable Long id) throws ApplicationsException {
        applicationsServiceImpl.deleteById(id);
        return ResponseEntity.ok("Apliación borrada: " + id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Applications> findById(@PathVariable Long id) throws ApplicationsException {
        logger.trace("Se busca aplicación con id: ", id);
        Applications search = this.applicationsServiceImpl.findById(id);

        return ResponseEntity.ok(search);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Applications> findByName(@PathVariable String name) throws ApplicationsException {
        logger.trace("Se busca aplicación con nombre: ", name);
        return ResponseEntity.ok(this.applicationsServiceImpl.findByName(name));
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Applications>> findAllByType(@PathVariable String type) throws ApplicationsException {
        logger.trace("Se busca aplicaciones por tipo: ", type);
        return ResponseEntity.ok(this.applicationsServiceImpl.findAllByType(type));
    
    }

    @GetMapping("/environments/{id}")
    public ResponseEntity<Applications> findApplicationsByEnvironmentsId(@PathVariable Long id) throws ApplicationsException {
        logger.trace("Se busca aplicación con id de entorno: ", id);
        return ResponseEntity.ok(this.applicationsServiceImpl.findApplicationsByEnvironmentsId(id));
    }


    @ExceptionHandler({ApplicationsException.class})
    public ResponseEntity<?> handlerApplicationsException(ApplicationsException applicationsException) {
        return new ResponseEntity<>(applicationsException.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
