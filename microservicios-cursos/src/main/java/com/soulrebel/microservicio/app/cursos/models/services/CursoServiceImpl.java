package com.soulrebel.microservicio.app.cursos.models.services;

import com.soulrebel.microservicio.app.cursos.clients.AlumnoFeignClient;
import com.soulrebel.microservicio.app.cursos.clients.RespuestaFeignClient;
import com.soulrebel.microservicio.app.cursos.models.entity.Curso;
import com.soulrebel.microservicio.app.cursos.models.repository.CursoRepository;
import com.soulrebel.microservicios.commons.alumnos.models.entity.Alumno;
import com.soulrebel.microservicios.commons.services.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CursoServiceImpl extends CommonServiceImpl<Curso, CursoRepository> implements CursoService {
    public CursoServiceImpl(CursoRepository repository, RespuestaFeignClient respuestaFeignClient, AlumnoFeignClient alumnoFeignClient) {
        super(repository);
        this.respuestaFeignClient = respuestaFeignClient;
        this.alumnoFeignClient = alumnoFeignClient;
    }

    private final RespuestaFeignClient respuestaFeignClient;
    private final AlumnoFeignClient alumnoFeignClient;

    @Override
    public Curso findCursoByAlumnosId(Long id) {
        return repository.findCursoByAlumnosId(id);
    }

    @Override
    public Iterable<Long> optenerExamenesIdsConRespuestasAlumno(Long alumnoId) {
        return respuestaFeignClient.optenerExamenesIdsConRespuestasAlumnos(alumnoId);
    }

    @Override
    public Iterable<Alumno> obtenerAlumnosPorCursos(Iterable<Long> ids) {
        return alumnoFeignClient.obtenerAlumnosPorCursos(ids);
    }

    @Override
    @Transactional
    public void eliminarCursoAlumnoPorId(Long id) {
        repository.eliminarCursoAlumnoPorId(id);
    }
}
