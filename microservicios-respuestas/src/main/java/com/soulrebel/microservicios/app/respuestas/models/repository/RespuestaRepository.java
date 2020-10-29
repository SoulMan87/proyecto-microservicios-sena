package com.soulrebel.microservicios.app.respuestas.models.repository;

import com.soulrebel.microservicios.app.respuestas.models.entity.Respuesta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RespuestaRepository extends CrudRepository<Respuesta, Long> {

    @Query("select r from Respuesta r join fetch r.alumno a join fetch r.pregunta p join fetch p.examen e where a.id=?1 and e.id=?2")
    Iterable<Respuesta> findRespuestaByAlumnoByExamen(Long alumnoId, Long examenId);
}
