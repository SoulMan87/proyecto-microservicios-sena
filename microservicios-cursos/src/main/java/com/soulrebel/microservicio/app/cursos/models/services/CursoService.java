package com.soulrebel.microservicio.app.cursos.models.services;

import com.soulrebel.microservicio.app.cursos.models.entity.Curso;
import com.soulrebel.microservicios.commons.services.CommonService;

public interface CursoService extends CommonService<Curso> {

    public Curso findCursoByAlumnosId(Long id);
}
