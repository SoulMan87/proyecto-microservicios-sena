package com.soulrebel.microservicio.app.cursos.models.services;

import com.soulrebel.microservicio.app.cursos.models.entity.Curso;
import com.soulrebel.microservicios.commons.alumnos.models.entity.Alumno;
import com.soulrebel.microservicios.commons.services.CommonService;


public interface CursoService extends CommonService<Curso> {

    Curso findCursoByAlumnosId(Long id);

    Iterable<Long> optenerExamenesIdsConRespuestasAlumno(Long alumnoId);

    Iterable<Alumno> obtenerAlumnosPorCursos(Iterable<Long> ids);

    void eliminarCursoAlumnoPorId(Long id);
}
