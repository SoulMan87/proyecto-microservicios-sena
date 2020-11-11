package com.soulrebel.microservicio.app.cursos.models.services;

import com.soulrebel.microservicio.app.cursos.models.entity.Curso;
import com.soulrebel.microservicios.commons.alumnos.models.entity.Alumno;
import com.soulrebel.microservicios.commons.services.CommonService;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface CursoService extends CommonService<Curso> {

    Curso findCursoByAlumnosId(Long id);

    Iterable<Long> optenerExamenesIdsConRespuestasAlumno(Long alumnoId);

    public Iterable<Alumno> obtenerAlumnosPorCursos(Iterable<Long> ids);

    public void eliminarCursoAlumnoPorId(Long id);
}
