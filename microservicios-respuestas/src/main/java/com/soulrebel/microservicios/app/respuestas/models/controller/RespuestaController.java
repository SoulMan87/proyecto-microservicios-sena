package com.soulrebel.microservicios.app.respuestas.models.controller;

import com.soulrebel.microservicios.app.respuestas.models.entity.Respuesta;
import com.soulrebel.microservicios.app.respuestas.models.services.RespuestaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class RespuestaController {

    private final RespuestaService service;

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Iterable<Respuesta> respuestas) {
        Iterable<Respuesta> respuestasList = service.saveAllResp(respuestas);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuestasList);
    }

    @GetMapping("/alumno/{alumnoId}/examen/{examenId}")
    public ResponseEntity<?> optenerRespuestaPorAlumnoPorExamen(@PathVariable Long alumnoId,@PathVariable Long examenId){
       Iterable<Respuesta>respuestas = service.findRespuestaByAlumnoByExamen(alumnoId,examenId);
       return ResponseEntity.ok(respuestas);
    }
}
