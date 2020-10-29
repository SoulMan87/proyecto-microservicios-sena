package com.soulrebel.microservicios.app.respuestas.models.repository;

import com.soulrebel.microservicios.app.respuestas.models.entity.Respuesta;
import org.springframework.data.repository.CrudRepository;

public interface RespuestaRepository extends CrudRepository<Respuesta, Long> {
}
