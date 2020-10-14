package com.soulrebel.microservicios.app.alumnos.models.controller;


import com.soulrebel.microservicios.commons.alumnos.models.entity.Alumno;
import com.soulrebel.microservicios.app.alumnos.models.service.AlumnoService;
import com.soulrebel.microservicios.commons.controllers.CommonController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AlumnoController extends CommonController<Alumno, AlumnoService> {

    public AlumnoController(AlumnoService service) {
        super(service);
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

        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveService(alumnoDb));
    }


}


