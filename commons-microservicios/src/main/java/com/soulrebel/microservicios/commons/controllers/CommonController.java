package com.soulrebel.microservicios.commons.controllers;


import com.soulrebel.microservicios.commons.services.CommonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

public class CommonController<E, S extends CommonService<E>> {

    protected final S service;

    public CommonController(S service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok().body(service.findAllService());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> ver(@PathVariable Long id) {
        Optional<E> optionalEntity = service.findByIdService(id);
        if (optionalEntity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optionalEntity.get());
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody E entity) {
        E entityDb = service.saveService(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(entityDb);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        service.deleteService(id);
        return ResponseEntity.noContent().build();
    }

}


