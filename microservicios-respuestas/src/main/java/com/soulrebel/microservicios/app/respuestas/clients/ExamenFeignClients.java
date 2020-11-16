package com.soulrebel.microservicios.app.respuestas.clients;


import com.soulrebel.microservicios.commons.examenes.models.entity.Examen;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservicio-examenes")
public interface ExamenFeignClients {

    @GetMapping("/{id}")
    Examen ObtenerExamenPorId(@PathVariable Long id);

}
