package com.soulrebel.microservicios.app.respuestas.models.services;

import com.soulrebel.microservicios.app.respuestas.models.entity.Respuesta;
import com.soulrebel.microservicios.app.respuestas.models.repository.RespuestaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("respuestaService")
@RequiredArgsConstructor
public class RespuestaServiceImpl implements RespuestaService {

    private final RespuestaRepository repository;

    @Override
    @Transactional
    public Iterable<Respuesta> saveAllResp(Iterable<Respuesta> respuestas) {
        return repository.saveAll(respuestas);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Respuesta> findRespuestaByAlumnoByExamen(Long alumnoId, Long examenId) {
        return null;
    }

    @Override
    public Iterable<Long> findExamenesIdsRespuestaByAlumno(Long alumnoId) {
        return null;
    }
}
