package com.soulrebel.microservicios.app.respuestas.models.controller;

import com.soulrebel.microservicios.app.respuestas.models.entity.Respuesta;
import com.soulrebel.microservicios.app.respuestas.models.services.RespuestaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
public class RespuestaController {

    private final RespuestaService service;

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Iterable<Respuesta> respuestas) {
        respuestas = ((List<Respuesta>)respuestas).stream().peek(respuesta -> {
            respuesta.setAlumnoId(respuesta.getAlumno().getId());
            respuesta.setPreguntaId(respuesta.getPregunta().getId());
        }).collect(Collectors.toList());
        Iterable<Respuesta> respuestasList = service.saveAllResp(respuestas);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuestasList);
    }

    @GetMapping("/alumno/{alumnoId}/examen/{examenId}")
    public ResponseEntity<?> optenerRespuestaPorAlumnoPorExamen(@PathVariable Long alumnoId,@PathVariable Long examenId){
       Iterable<Respuesta>respuestas = service.findRespuestaByAlumnoByExamen(alumnoId,examenId);
       return ResponseEntity.ok(respuestas);
    }

    @GetMapping("/alumno/{alumnoId}/examenes-respondidos")
    public ResponseEntity<?> optenerExamenesIdsConRespuestasAlumnos(@PathVariable Long alumnoId){
        Iterable<Long> examenesIds = service.findExamenesIdsRespuestaByAlumno(alumnoId);
        return ResponseEntity.ok(examenesIds);
    }
}
