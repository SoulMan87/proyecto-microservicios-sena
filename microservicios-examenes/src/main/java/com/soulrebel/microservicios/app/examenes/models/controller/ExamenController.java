package com.soulrebel.microservicios.app.examenes.models.controller;

import com.soulrebel.microservicios.app.examenes.models.services.ExamenService;
import com.soulrebel.microservicios.commons.controllers.CommonController;
import com.soulrebel.microservicios.commons.examenes.models.entity.Examen;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ExamenController extends CommonController<Examen, ExamenService> {
    public ExamenController(ExamenService service) {
        super(service);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestBody Examen examen, @PathVariable Long id) {
        Optional<Examen> optionalExamen = service.findByIdService(id);
        if (optionalExamen.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Examen examenDb = optionalExamen.get();
        examenDb.setNombre(examen.getNombre());


        examenDb.getPreguntas()
                .stream()
                .filter(pregunta -> !examen.getPreguntas().contains(pregunta))
                .forEach(examenDb::removePregunta);

        examenDb.setPreguntas(examen.getPreguntas());

        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveService(examenDb));
    }
}
