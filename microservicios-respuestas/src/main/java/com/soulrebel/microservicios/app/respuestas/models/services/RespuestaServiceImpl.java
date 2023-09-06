package com.soulrebel.microservicios.app.respuestas.models.services;

import com.soulrebel.microservicios.app.respuestas.models.entity.Respuesta;
import com.soulrebel.microservicios.app.respuestas.models.repository.RespuestaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("respuestaService")
@RequiredArgsConstructor
public class RespuestaServiceImpl implements RespuestaService {

    private final RespuestaRepository repository;

    @Override
    public Iterable<Respuesta> saveAllResp(Iterable<Respuesta> respuestas) {
        return repository.saveAll(respuestas);
    }

    @Override
    public Iterable<Respuesta> findRespuestaByAlumnoByExamen(Long alumnoId, Long examenId) {

        return repository.findRespuestaByAlumnoByExamen (alumnoId, examenId);
    }

    @Override
    public Iterable<Long> findExamenesIdsRespuestaByAlumno(Long alumnoId) {

        return getLongs (alumnoId);
    }

    @Override
    public Iterable<Respuesta> findByAlumnoId(Long alumnoId) {
        return repository.findByAlumnoId (alumnoId);
    }

    private List<Long> getLongs(Long alumnoId) {
        List<Respuesta> respuestasAlumno = (List<Respuesta>) repository.findExamenesIdsRespuestaByAlumno(alumnoId);
        return respuestasAlumno
                .stream ()
                .map (respuesta -> respuesta.getPregunta ().getExamen ().getId ())
                .distinct ()
                .collect (Collectors.toList ());
    }
}
