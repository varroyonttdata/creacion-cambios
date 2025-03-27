package com.nttdata.caixa.gestion.cloud.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.caixa.gestion.cloud.backend.entities.Person;
import com.nttdata.caixa.gestion.cloud.backend.exceptions.PersonException;



@RestController
@RequestMapping("/hello")
public class HelloWorld {

    @GetMapping
    public String hello() {
        return "Hello World";
    }

    @RequestMapping(path = "/{name}",method = RequestMethod.GET, produces = "application/json", consumes = "application/json")
    public String helloname(@PathVariable String name) {
        return "Hello " + name;
    }

    @GetMapping("/person")
    public String helloperson(@RequestBody Person person) {
        return "Hello " + person.getName();
    }

    @GetMapping("/test")
    public ResponseEntity<String> test(@RequestBody Person person) throws PersonException {
        if (person == null || person.getName() == null || person.getName().isEmpty() ) {
            throw new PersonException("Esta persona no tiene nombre");
        }
        return ResponseEntity.ok("Hello " + person.getName());
    }

    @ExceptionHandler({PersonException.class})
    public ResponseEntity<String> handlePersonException(PersonException personException) {
        return new ResponseEntity<>(personException.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
