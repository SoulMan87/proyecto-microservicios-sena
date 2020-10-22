package com.soulrebel.microservicios.app.examenes.models.services;

import com.soulrebel.microservicios.app.examenes.models.repository.ExamenRepository;
import com.soulrebel.microservicios.commons.examenes.models.entity.Examen;
import com.soulrebel.microservicios.commons.services.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("examenService")
public class ExamenServiceImpl extends CommonServiceImpl<Examen, ExamenRepository>implements ExamenService{
    public ExamenServiceImpl(ExamenRepository repository) {
        super(repository);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Examen> findByNombre(String term) {
        return repository.findByNombre(term);
    }
}
