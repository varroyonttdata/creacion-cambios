package com.nttdata.caixa.gestion.cloud.backend.controller;

import java.util.List;

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

import com.nttdata.caixa.gestion.cloud.backend.entities.Component;
import com.nttdata.caixa.gestion.cloud.backend.entities.ComponentEnvironment;
import com.nttdata.caixa.gestion.cloud.backend.entities.dto.ComponentDTO;
import com.nttdata.caixa.gestion.cloud.backend.entities.enums.ComponentType;
import com.nttdata.caixa.gestion.cloud.backend.exceptions.ComponentException;
import com.nttdata.caixa.gestion.cloud.backend.services.implementations.ComponentServiceImpl;

@RestController
@RequestMapping("/component")
public class ComponentController {

    private ComponentServiceImpl componentServiceImpl;

    public ComponentController(ComponentServiceImpl componentServiceImpl) {
        this.componentServiceImpl = componentServiceImpl;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ComponentDTO> findComponentById (@PathVariable Long id) throws ComponentException {
        ComponentDTO searched = componentServiceImpl.findById(id);
        return ResponseEntity.ok(searched);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ComponentDTO> findComponentByName (@PathVariable String name) throws ComponentException {
        ComponentDTO searched = componentServiceImpl.findByName(name);
        return ResponseEntity.ok(searched);
    }

    @GetMapping("/type/{componentTypeString}")
    public ResponseEntity<List<ComponentDTO>> findAllComponentByType (@PathVariable String componentTypeString) throws ComponentException {
        // Convertir el String a un enum
        ComponentType componentType = ComponentType.valueOf(componentTypeString.toUpperCase());
        List<ComponentDTO> searched = componentServiceImpl.findAllByComponentType(componentType);
        return ResponseEntity.ok(searched);
    }

    @PostMapping("/create")
    public ResponseEntity<ComponentDTO> createComponent (@RequestBody Component component) throws ComponentException {
        ComponentDTO created = componentServiceImpl.createComponent(component);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/update")
    public ResponseEntity<ComponentDTO> updateComponent (@RequestBody Component component) throws ComponentException {
        ComponentDTO updated = componentServiceImpl.updateComponent(component);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteComponentById (@PathVariable Long id) throws ComponentException {
        componentServiceImpl.deleteComponentById(id);
        return ResponseEntity.ok("Componente borrado: " + id);
    }

    @PutMapping("/add/{componentId}")
    public ResponseEntity<ComponentDTO> addComponentEnvironmentToComponent(@RequestBody ComponentEnvironment componentEnvironment, @PathVariable Long componentId) throws ComponentException {
        return ResponseEntity.ok(componentServiceImpl.addComponentEnvironmentToComponent(componentEnvironment, componentId));
    }
  
    @ExceptionHandler({ComponentException.class})
    public ResponseEntity<?> handlerComponentException(ComponentException componentException) {
        return new ResponseEntity<>(componentException.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
