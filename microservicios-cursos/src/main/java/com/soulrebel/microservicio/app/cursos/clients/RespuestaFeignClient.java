package com.soulrebel.microservicio.app.cursos.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservicio-respuestas")
public interface RespuestaFeignClient {

    @GetMapping("/alumno/{alumnoId}/examenes-respondidos")
    Iterable<Long> optenerExamenesIdsConRespuestasAlumnos(@PathVariable Long alumnoId);
}
