package com.soulrebel.microservicios.app.respuestas.models.services;

import com.soulrebel.microservicios.app.respuestas.clients.ExamenFeignClients;
import com.soulrebel.microservicios.app.respuestas.models.entity.Respuesta;
import com.soulrebel.microservicios.app.respuestas.models.repository.RespuestaRepository;
import com.soulrebel.microservicios.commons.examenes.models.entity.Examen;
import com.soulrebel.microservicios.commons.examenes.models.entity.Pregunta;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("respuestaService")
@RequiredArgsConstructor
public class RespuestaServiceImpl implements RespuestaService {

    private final RespuestaRepository repository;

    private final ExamenFeignClients examenClient;

    @Override
    public Iterable<Respuesta> saveAllResp(Iterable<Respuesta> respuestas) {
        return repository.saveAll(respuestas);
    }

    @Override
    public Iterable<Respuesta> findRespuestaByAlumnoByExamen(Long alumnoId, Long examenId) {

        Examen examen = examenClient.ObtenerExamenPorId(examenId);
        List<Pregunta> preguntas = examen.getPreguntas();
        List<Long> preguntasIds = preguntas.stream().map(Pregunta::getId).collect(Collectors.toList());
        List<Respuesta> respuestas = (List<Respuesta>) repository.findRespuestaByAlumnoByPreguntaId(alumnoId, preguntasIds);
        respuestas = respuestas.stream().peek(respuesta -> preguntas.forEach(pregunta -> {
            if (pregunta.getId().equals(respuesta.getPreguntaId())) {
                respuesta.setPregunta(pregunta);
            }
        })).collect(Collectors.toList());
        return respuestas;
    }

    @Override
    public Iterable<Long> findExamenesIdsRespuestaByAlumno(Long alumnoId) {
        return null;
    }

    @Override
    public Iterable<Respuesta> findByAlumnoId(Long alumnoId) {
        return repository.findByAlumnoId(alumnoId);
    }
}
