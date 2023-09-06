package com.soulrebel.microservicios.app.respuestas.models.controller;

import com.soulrebel.microservicios.app.respuestas.models.entity.Respuesta;
import com.soulrebel.microservicios.app.respuestas.models.services.RespuestaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@RestController
@RequiredArgsConstructor
public class RespuestaController {

    private final RespuestaService service;

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Iterable<Respuesta> respuestas) {
        Iterable<Respuesta> respuestasList = service.saveAllResp (getRespuestas (respuestas));
        return ResponseEntity.status(HttpStatus.CREATED).body(respuestasList);
    }

    @GetMapping("/alumno/{alumnoId}/examen/{examenId}")
    public ResponseEntity<?> optenerRespuestaPorAlumnoPorExamen(@PathVariable Long alumnoId,
                                                                @PathVariable Long examenId){
       Iterable<Respuesta>respuestas = service.findRespuestaByAlumnoByExamen(alumnoId,examenId);
       return ResponseEntity.ok(respuestas);
    }

    @GetMapping("/alumno/{alumnoId}/examenes-respondidos")
    public ResponseEntity<?> optenerExamenesIdsConRespuestasAlumnos(@PathVariable Long alumnoId){
        Iterable<Long> examenesIds = service.findExamenesIdsRespuestaByAlumno(alumnoId);
        return ResponseEntity.ok(examenesIds);
    }

    private Iterable<Respuesta> getRespuestas(Iterable<Respuesta> respuestas) {
        return StreamSupport.stream (respuestas.spliterator (), false)
                .map (respuesta -> Respuesta.builder ()
                        .alumnoId (respuesta.getAlumno ().getId ())
                        .preguntaId (respuesta.getPregunta ().getId ())
                        .build ())
                .collect (Collectors.toList ());
    }
}
