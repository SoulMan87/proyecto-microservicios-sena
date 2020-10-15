package com.soulrebel.microservicio.app.cursos.models.services;

import com.soulrebel.microservicio.app.cursos.models.entity.Curso;
import com.soulrebel.microservicio.app.cursos.models.repository.CursoRepository;
import com.soulrebel.microservicios.commons.services.CommonServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CursoServiceImpl extends CommonServiceImpl<Curso, CursoRepository> implements CursoService {
    public CursoServiceImpl(CursoRepository repository) {
        super(repository);
    }

    @Override
    public Curso findCursoByAlumnosId(Long id) {
        return repository.findCursoByAlumnosId(id);
    }
}
