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

import com.nttdata.caixa.gestion.cloud.backend.entities.Application;
import com.nttdata.caixa.gestion.cloud.backend.entities.Component;
import com.nttdata.caixa.gestion.cloud.backend.entities.dto.ApplicationDTO;
import com.nttdata.caixa.gestion.cloud.backend.entities.enums.AppType;
import com.nttdata.caixa.gestion.cloud.backend.exceptions.ApplicationException;
import com.nttdata.caixa.gestion.cloud.backend.exceptions.EnvironmentException;
import com.nttdata.caixa.gestion.cloud.backend.services.implementations.ApplicationServiceImpl;

@RestController
@RequestMapping("/application")
public class ApplicationController {

    private static final Logger logger = LogManager.getLogger(ApplicationController.class);

    private ApplicationServiceImpl applicationServiceImpl;

    public ApplicationController(ApplicationServiceImpl applicationServiceImpl) {
        this.applicationServiceImpl = applicationServiceImpl;
    }

    @PostMapping("/create")
    public ResponseEntity<ApplicationDTO> createApplication (@RequestBody Application application) throws ApplicationException{
        logger.info("Aplicación creada: " + application);
        return ResponseEntity.ok(applicationServiceImpl.createApplication(application));
    }

    @PutMapping("/update")
    public ResponseEntity<ApplicationDTO> updateApplication (@RequestBody Application application) throws ApplicationException{
        ApplicationDTO updated = applicationServiceImpl.updateApplication(application);
        logger.info("Aplicación modificada: " + updated);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteApplication (@PathVariable Long id) throws ApplicationException {
        applicationServiceImpl.deleteById(id);
        return ResponseEntity.ok("Apliación borrada: " + id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ApplicationDTO> findById(@PathVariable Long id) throws ApplicationException {
        logger.trace("Se busca aplicación con id: ", + id);
        ApplicationDTO search = this.applicationServiceImpl.findById(id);

        return ResponseEntity.ok(search);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ApplicationDTO> findByName(@PathVariable String name) throws ApplicationException {
        logger.trace("Se busca aplicación con nombre: " + name);
        return ResponseEntity.ok(this.applicationServiceImpl.findByName(name));
    }

    //La llamada tiene que venir con el formato adecuado Primera Mayuscula y Ultima mayuscula.
    @GetMapping("/appType/{appType}")
    public ResponseEntity<List<ApplicationDTO>> findAllByAppType(@PathVariable String appType) throws ApplicationException {
        logger.trace("Se busca aplicaciones por tipo: " + appType);
        AppType enumType = AppType.valueOf(appType);
        List<ApplicationDTO> searched = this.applicationServiceImpl.findAllByAppType(enumType);
        return ResponseEntity.ok(searched);
    }

    
    @GetMapping("/component/{id}")
    public ResponseEntity<ApplicationDTO> findApplicationByComponentId(@PathVariable Long id) throws ApplicationException {
        logger.trace("Se busca aplicación con id de componente: ", id);
        return ResponseEntity.ok(this.applicationServiceImpl.findApplicationByComponentId(id));
    }

    
    @PutMapping("/add/{id}")
    public ResponseEntity<ApplicationDTO> updateEnvironmentsToApplication(@RequestBody Component component, @PathVariable Long id) throws ApplicationException {

        return ResponseEntity.ok(this.applicationServiceImpl.updateComponentToApplication(component, id));
    }

    //TODO Meter mas excepciones en los servicios???
    @ExceptionHandler({EnvironmentException.class, ApplicationException.class})
    public ResponseEntity<?> handlerApplicationException(ApplicationException applicationException) {
        return new ResponseEntity<>(applicationException.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
