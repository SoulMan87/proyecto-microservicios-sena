package com.soulrebel.microservicio.app.cursos.models.controller;

import com.soulrebel.microservicio.app.cursos.models.entity.Curso;
import com.soulrebel.microservicio.app.cursos.models.services.CursoService;
import com.soulrebel.microservicios.commons.alumnos.models.entity.Alumno;
import com.soulrebel.microservicios.commons.controllers.CommonController;
import com.soulrebel.microservicios.commons.examenes.models.entity.Examen;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/{id}/eliminar-alumno")
    public ResponseEntity<?> eliminarAlumno(@RequestBody Alumno alumno, @PathVariable Long id) {
        Optional<Curso> optionalCurso = this.service.findByIdService(id);
        if (optionalCurso.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Curso cursoDb = optionalCurso.get();

        cursoDb.removeAlumno(alumno);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.saveService(cursoDb));
    }

    @GetMapping("/alumno/{id}")
    public ResponseEntity<?> buscarAlumnoId(@PathVariable Long id) {
        Curso curso = service.findCursoByAlumnosId(id);
        return ResponseEntity.ok(curso);
    }

    @PutMapping("/{id}/asignar-examenes")
    public ResponseEntity<?> asignarExamenes(@RequestBody List<Examen> examenes, @PathVariable Long id) {
        Optional<Curso> optionalCursoExamenes = this.service.findByIdService(id);
        if (optionalCursoExamenes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Curso cursoDb = optionalCursoExamenes.get();
        examenes.forEach(cursoDb::addExamen);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.saveService(cursoDb));
    }

    @PutMapping("/{id}/eliminar-examen")
    public ResponseEntity<?> eliminarExamen(@RequestBody Examen examenes, @PathVariable Long id) {
        Optional<Curso> optionalCursoExamenes = this.service.findByIdService(id);
        if (optionalCursoExamenes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Curso cursoDb = optionalCursoExamenes.get();

        cursoDb.removeExamen(examenes);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.saveService(cursoDb));
    }
}
