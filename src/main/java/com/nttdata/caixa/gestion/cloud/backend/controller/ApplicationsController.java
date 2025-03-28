package com.nttdata.caixa.gestion.cloud.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.caixa.gestion.cloud.backend.entities.Applications;
import com.nttdata.caixa.gestion.cloud.backend.exceptions.ApplicationsException;
import com.nttdata.caixa.gestion.cloud.backend.services.implementations.ApplicationsServiceImpl;

@RestController
@RequestMapping("/applications")
public class ApplicationsController {

    private ApplicationsServiceImpl applicationsServiceImpl;

    

    public ApplicationsController(ApplicationsServiceImpl applicationsServiceImpl) {
        this.applicationsServiceImpl = applicationsServiceImpl;
    }



    @GetMapping("/id/{id}")
    public ResponseEntity<Applications> findById(@PathVariable Long id) throws ApplicationsException {

        Applications search = this.applicationsServiceImpl.findById(id);

        return ResponseEntity.ok(search);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Applications> findByName(@PathVariable String name) throws ApplicationsException {
        return ResponseEntity.ok(this.applicationsServiceImpl.findByName(name));
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Applications>> findAllByType(@PathVariable String type) throws ApplicationsException {

            return ResponseEntity.ok(this.applicationsServiceImpl.findAllByType(type));
    
    }

    @GetMapping("/environments/{id}")
    public ResponseEntity<Applications> findApplicationsByEnvironmentsId(@PathVariable Long id) throws ApplicationsException {
        return ResponseEntity.ok(this.applicationsServiceImpl.findApplicationsByEnvironmentsId(id));
    }


    @ExceptionHandler({ApplicationsException.class})
    public ResponseEntity<?> handlerApplicationsException(ApplicationsException applicationsException) {
        return new ResponseEntity<>(applicationsException.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
