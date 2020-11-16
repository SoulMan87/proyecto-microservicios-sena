package com.soulrebel.microservicios.app.respuestas.models.repository;

import com.soulrebel.microservicios.app.respuestas.models.entity.Respuesta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


public interface RespuestaRepository extends MongoRepository<Respuesta, String> {

    //@Query("select r from Respuesta r join fetch r.pregunta p join fetch p.examen e where r.alumnoId=?1 and e.id=?2")
    //Iterable<Respuesta> findRespuestaByAlumnoByExamen(Long alumnoId, Long examenId);
    @Query("{'alumnoId':?0, 'preguntaId': { $in: ?1}}")
    Iterable<Respuesta> findRespuestaByAlumnoByPreguntaId(Long alumnoId, Iterable<Long> preguntaIds);

    @Query("{'alumnoId': ?0}")
    Iterable<Respuesta> findByAlumnoId(Long alumnoId);

    @Query("{'alumnoId': ?0, 'pregunta.examen.id': ?1}")
    Iterable<Respuesta> findRespuestaByAlumnoByExamen(Long alumnoId, Long examenId);

    @Query(value = "{'alumnoId': ?0}", fields = "{'pregunta.examen.id': 1}")
    Iterable<Respuesta> findExamenesIdsRespuestaByAlumno(Long alumnoId);

    //@Query("select e.id from Respuesta r  join r.pregunta p join p.examen e where r.alumnoId=?1 group by e.id")
    // Iterable<Long> findExamenesIdsRespuestaByAlumno(Long alumnoId);
}
