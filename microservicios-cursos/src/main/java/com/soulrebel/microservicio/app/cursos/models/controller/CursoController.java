package com.soulrebel.microservicio.app.cursos.models.controller;

import com.soulrebel.microservicio.app.cursos.models.entity.Curso;
import com.soulrebel.microservicio.app.cursos.models.entity.CursoAlumno;
import com.soulrebel.microservicio.app.cursos.models.services.CursoService;
import com.soulrebel.microservicios.commons.alumnos.models.entity.Alumno;
import com.soulrebel.microservicios.commons.controllers.CommonController;
import com.soulrebel.microservicios.commons.examenes.models.entity.Examen;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class CursoController extends CommonController<Curso, CursoService> {
    public CursoController(CursoService service) {
        super(service);
    }

    @Value("${config.balanceador.test}")
    private String balanceadorTest;

    @GetMapping
    @Override
    public ResponseEntity<?> listar() {
        List<Curso> cursos = ((List<Curso>) service.findAllService()).stream()
                .peek(curso -> curso.getCursoAlumnos().forEach(cursoAlumno -> {
                    Alumno alumno = new Alumno();
                    alumno.setId(cursoAlumno.getId());
                    curso.addAlumno(alumno);
                })).collect(Collectors.toList());
        return ResponseEntity.ok().body(cursos);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<?> ver(@PathVariable Long id) {
        Optional<Curso> optionalCurso = service.findByIdService(id);
        if (optionalCurso.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Curso curso = optionalCurso.get();
        if (!curso.getCursoAlumnos().isEmpty()) {
            List<Long> ids = curso.getCursoAlumnos()
                    .stream()
                    .map(CursoAlumno::getAlumnoId)
                    .collect(Collectors.toList());
            List<Alumno> alumnos = (List<Alumno>) service.obtenerAlumnosPorCursos(ids);
            curso.setAlumnos(alumnos);
        }
        return ResponseEntity.ok().body(curso);
    }

    @GetMapping("/balanceador-test")
    public ResponseEntity<?> balanceadorTest() {
        Map<String, Object> response = new HashMap<>();
        response.put("balanceador", balanceadorTest);
        response.put("cursos", service.findAllService());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Curso curso, BindingResult result, @PathVariable Long id) {

        if (result.hasErrors()) {
            return this.validar(result);
        }
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
        alumnos.forEach(alumno -> {
            CursoAlumno cursoAlumno = new CursoAlumno();
            cursoAlumno.setAlumnoId(alumno.getId());
            cursoAlumno.setCurso(cursoDb);
            cursoDb.addCursoAlumno(cursoAlumno);
        });
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.saveService(cursoDb));
    }

    @PutMapping("/{id}/eliminar-alumno")
    public ResponseEntity<?> eliminarAlumno(@RequestBody Alumno alumno, @PathVariable Long id) {
        Optional<Curso> optionalCurso = this.service.findByIdService(id);
        if (optionalCurso.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Curso cursoDb = optionalCurso.get();
        CursoAlumno cursoAlumno = new CursoAlumno();
        cursoAlumno.setAlumnoId(alumno.getId());
        cursoDb.removeCursoAlumno(cursoAlumno);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.saveService(cursoDb));
    }

    @GetMapping("/alumno/{id}")
    public ResponseEntity<?> buscarAlumnoId(@PathVariable Long id) {
        Curso curso = service.findCursoByAlumnosId(id);

        if (curso != null) {
            List<Long> examenesId = (List<Long>) service.optenerExamenesIdsConRespuestasAlumno(id);

            List<Examen> examenes = curso.getExamenes().stream().map(examen -> {
                if (examenesId.contains(examen.getId())) {
                    examen.setRespondido(true);
                }
                return examen;
            }).collect(Collectors.toList());
            curso.setExamenes(examenes);
        }
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
