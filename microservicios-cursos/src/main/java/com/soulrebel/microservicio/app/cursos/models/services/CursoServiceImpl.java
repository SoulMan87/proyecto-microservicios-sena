package com.soulrebel.microservicio.app.cursos.models.services;

import com.soulrebel.microservicio.app.cursos.clients.RespuestaFeignClient;
import com.soulrebel.microservicio.app.cursos.models.entity.Curso;
import com.soulrebel.microservicio.app.cursos.models.repository.CursoRepository;
import com.soulrebel.microservicios.commons.services.CommonServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CursoServiceImpl extends CommonServiceImpl<Curso, CursoRepository> implements CursoService {
    public CursoServiceImpl(CursoRepository repository, RespuestaFeignClient feignClient) {
        super(repository);
        this.feignClient = feignClient;
    }
    private final RespuestaFeignClient feignClient;

    @Override
    public Curso findCursoByAlumnosId(Long id) {
        return repository.findCursoByAlumnosId(id);
    }

    @Override
    public Iterable<Long> optenerExamenesIdsConRespuestasAlumno(Long alumnoId) {
        return feignClient.optenerExamenesIdsConRespuestasAlumnos(alumnoId);
    }
}
