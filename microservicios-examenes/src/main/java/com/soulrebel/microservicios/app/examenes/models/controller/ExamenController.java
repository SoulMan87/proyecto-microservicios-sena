package com.soulrebel.microservicios.app.examenes.models.controller;

import com.soulrebel.microservicios.app.examenes.models.services.ExamenService;
import com.soulrebel.microservicios.commons.controllers.CommonController;
import com.soulrebel.microservicios.commons.examenes.models.entity.Examen;
import com.soulrebel.microservicios.commons.examenes.models.entity.Pregunta;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ExamenController extends CommonController<Examen, ExamenService> {
    public ExamenController(ExamenService service) {
        super(service);
    }

    @GetMapping("/respondidos-por-preguntas")
    public ResponseEntity<?> obteberExamenesPorIdsPorIdRespondidas(@RequestParam List<Long> preguntaIds) {
        return ResponseEntity.ok().body(service.findExamenesIdsRespuestaByPreguntaIds(preguntaIds));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Examen examen, BindingResult result, @PathVariable Long id) {

        if (result.hasErrors()) {
            return this.validar(result);
        }
        Optional<Examen> optionalExamen = service.findByIdService(id);
        if (optionalExamen.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Examen examenDb = optionalExamen.get();
        examenDb.setNombre(examen.getNombre());


        List<Pregunta> eliminadas = examenDb.getPreguntas()
                .stream()
                .filter(pregunta -> !examen.getPreguntas().contains(pregunta))
                .collect(Collectors.toList());
        eliminadas.forEach(examenDb::removePregunta);

        examenDb.setPreguntas(examen.getPreguntas());
        examenDb.setAsignaturaHija(examen.getAsignaturaHija());
        examenDb.setAsignaturaPadre(examen.getAsignaturaPadre());

        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveService(examenDb));
    }

    @GetMapping("/filtrar/{term}")
    public ResponseEntity<?> filtrar(@PathVariable String term) {
        return ResponseEntity.ok(service.findByNombre(term));
    }

    @GetMapping("/asignaturas")
    public ResponseEntity<?> listarAsignaturas() {
        return ResponseEntity.ok(service.findAllAsignaturas());
    }
}
