package com.soulrebel.microservicios.app.examenes.models.services;

import com.soulrebel.microservicios.app.examenes.models.repository.AsignarutaRepository;
import com.soulrebel.microservicios.app.examenes.models.repository.ExamenRepository;
import com.soulrebel.microservicios.commons.examenes.models.entity.Asignatura;
import com.soulrebel.microservicios.commons.examenes.models.entity.Examen;
import com.soulrebel.microservicios.commons.services.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("examenService")
public class ExamenServiceImpl extends CommonServiceImpl<Examen, ExamenRepository> implements ExamenService {
    public ExamenServiceImpl(ExamenRepository repository, AsignarutaRepository asignarutaRepository) {
        super(repository);
        this.asignarutaRepository = asignarutaRepository;
    }

    private final AsignarutaRepository asignarutaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Examen> findByNombre(String term) {
        return repository.findByNombre(term);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Asignatura> findAllAsignaturas() {
        return asignarutaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Long> findExamenesIdsRespuestaByPreguntaIds(Iterable<Long> preguntaIds) {
        return repository.findExamenesIdsRespuestaByPreguntaIds(preguntaIds);
    }

}
