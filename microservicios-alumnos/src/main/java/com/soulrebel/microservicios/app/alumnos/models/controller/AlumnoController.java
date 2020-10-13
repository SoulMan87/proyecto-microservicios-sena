package com.soulrebel.microservicios.app.alumnos.models.controller;


import com.soulrebel.microservicios.app.alumnos.models.entity.Alumno;
import com.soulrebel.microservicios.app.alumnos.models.service.AlumnoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
public class AlumnoController {

    private final AlumnoService service;

    @GetMapping("/")
    public ResponseEntity<?> listarAlumnos() {
        return ResponseEntity.ok().body(service.findAllService());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> verAlumnos(@PathVariable Long id) {
        Optional<Alumno> optionalAlumno = service.findByIdService(id);
        if (optionalAlumno.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optionalAlumno.get());
    }

    @PutMapping("/")
    public ResponseEntity<?> crearAlumno(@RequestBody Alumno alumno) {
        Alumno alumnoDb = service.saveAlumnoService(alumno);
        return ResponseEntity.status(HttpStatus.CREATED).body(alumnoDb);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarAlumnos(@RequestBody Alumno alumno, @PathVariable Long id) {
        Optional<Alumno> optionalAlumno = service.findByIdService(id);
        if (optionalAlumno.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Alumno alumnoDb = optionalAlumno.get();
        alumno.setNombre(alumno.getNombre());
        alumno.setApellido(alumno.getApellido());
        alumno.setEmail(alumno.getEmail());

        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveAlumnoService(alumnoDb));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarAlumos(@PathVariable Long id) {
        service.deleteAlumnoService(id);
        return ResponseEntity.noContent().build();
    }

}


