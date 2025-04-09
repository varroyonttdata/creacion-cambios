package com.nttdata.caixa.gestion.cloud.backend.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.caixa.gestion.cloud.backend.entities.ComponentEnvironment;
import com.nttdata.caixa.gestion.cloud.backend.entities.dto.ComponentEnvironmentDTO;
import com.nttdata.caixa.gestion.cloud.backend.exceptions.ComponentEnvironmentException;
import com.nttdata.caixa.gestion.cloud.backend.exceptions.ComponentException;
import com.nttdata.caixa.gestion.cloud.backend.exceptions.EnvironmentException;
import com.nttdata.caixa.gestion.cloud.backend.services.ComponentEnvironmentService;

@RestController
@RequestMapping("/component-environment")
public class ComponentEnvironmentController {

    private final ComponentEnvironmentService componentEnvironmentService;

    public ComponentEnvironmentController(ComponentEnvironmentService componentEnvironmentService) {
        this.componentEnvironmentService = componentEnvironmentService;
    }

    @GetMapping("id/{id}")
    public ResponseEntity<ComponentEnvironmentDTO> findById(@PathVariable Long id) throws ComponentEnvironmentException{
        ComponentEnvironmentDTO searched = componentEnvironmentService.findById(id);
        return ResponseEntity.ok(searched);
    }

    @PostMapping("/create")
    public ResponseEntity<ComponentEnvironmentDTO> createComponentEnvironment(@RequestParam Integer replica, @RequestParam Long componentId, @RequestParam Long environmentId) throws ComponentEnvironmentException, ComponentException, EnvironmentException{
        ComponentEnvironmentDTO created = componentEnvironmentService.createComponentEnvironment(replica, componentId, environmentId);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/update")
    public ResponseEntity<ComponentEnvironmentDTO> updateComponentEnvironment(@RequestBody ComponentEnvironment componentEnvironment) throws ComponentEnvironmentException{
        ComponentEnvironmentDTO updated = componentEnvironmentService.updateComponentEnvironment(componentEnvironment);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteComponentEnvironment(@PathVariable Long id) throws ComponentEnvironmentException{
        componentEnvironmentService.deleteComponentEnvironmentById(id);
        return ResponseEntity.ok("Componente borrado: " + id);
    }

    @ExceptionHandler({ComponentEnvironmentException.class, EnvironmentException.class, ComponentException.class})
    public ResponseEntity<?> handlerComponentEnvironmentException(ComponentEnvironmentException componentEnvironmentException) {
        return new ResponseEntity<>(componentEnvironmentException.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
