package com.soulrebel.microservicio.app.cursos.models.services;

import com.soulrebel.microservicio.app.cursos.models.entity.Curso;
import com.soulrebel.microservicios.commons.alumnos.models.entity.Alumno;
import com.soulrebel.microservicios.commons.services.CommonService;
import org.springframework.web.bind.annotation.RequestParam;

public interface CursoService extends CommonService<Curso> {

    Curso findCursoByAlumnosId(Long id);

    Iterable<Long> optenerExamenesIdsConRespuestasAlumno(Long alumnoId);

    public Iterable<Alumno> obtenerAlumnosPorCursos(@RequestParam Iterable<Long> ids);
}
