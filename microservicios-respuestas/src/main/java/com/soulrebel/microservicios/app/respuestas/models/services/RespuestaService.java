package com.soulrebel.microservicios.app.respuestas.models.services;

import com.soulrebel.microservicios.app.respuestas.models.entity.Respuesta;

public interface RespuestaService {

    Iterable<Respuesta> saveAllResp(Iterable<Respuesta> respuestas);

    Iterable<Respuesta> findRespuestaByAlumnoByExamen(Long alumnoId, Long examenId);
}
