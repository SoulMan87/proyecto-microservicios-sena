package com.soulrebel.microservicio.app.cursos.models.controller;

import com.soulrebel.microservicio.app.cursos.models.entity.Curso;
import com.soulrebel.microservicio.app.cursos.models.services.CursoService;
import com.soulrebel.microservicios.commons.alumnos.models.entity.Alumno;
import com.soulrebel.microservicios.commons.controllers.CommonController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class CursoController extends CommonController<Curso, CursoService> {
    public CursoController(CursoService service) {
        super(service);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestBody Curso curso, @PathVariable Long id) {
        Optional<Curso> optionalCurso = this.service.findByIdService(id);
        if (optionalCurso.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Curso cursoDb = optionalCurso.get();
        cursoDb.setNombre(curso.getNombre());
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.saveService(curso));
    }

    @PutMapping("/{id}/asignar-alumnos")
    public ResponseEntity<?> asignarAlumnos(@RequestBody List<Alumno> alumnos, @PathVariable Long id) {
        Optional<Curso> optionalCurso = this.service.findByIdService(id);
        if (optionalCurso.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Curso cursoDb = optionalCurso.get();
        alumnos.forEach(cursoDb::addAlumno);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.saveService(cursoDb));
    }

    @PutMapping("/{id}/elimina-alumno")
    public ResponseEntity<?> eliminaAlumno(@RequestBody Alumno alumno, @PathVariable Long id) {
        Optional<Curso> optionalCurso = this.service.findByIdService(id);
        if (optionalCurso.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Curso cursoDb = optionalCurso.get();

        cursoDb.removeAlumno(alumno);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.saveService(cursoDb));
    }
}
