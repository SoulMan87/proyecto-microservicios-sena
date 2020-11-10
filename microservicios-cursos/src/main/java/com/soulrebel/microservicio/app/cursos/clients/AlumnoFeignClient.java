package com.soulrebel.microservicio.app.cursos.clients;

import com.soulrebel.microservicios.commons.alumnos.models.entity.Alumno;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "microservicio-alumnos")
public interface AlumnoFeignClient {

    @GetMapping("/alumnos-por-curso")
    public Iterable<Alumno> obtenerAlumnosPorCursos(@RequestParam Iterable<Long> ids);
}
