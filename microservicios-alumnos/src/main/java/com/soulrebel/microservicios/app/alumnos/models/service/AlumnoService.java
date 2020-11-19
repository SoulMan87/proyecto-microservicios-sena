package com.soulrebel.microservicios.app.alumnos.models.service;

import com.soulrebel.microservicios.commons.alumnos.models.entity.Alumno;
import com.soulrebel.microservicios.commons.services.CommonService;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


public interface AlumnoService extends CommonService<Alumno> {

    List<Alumno> findByNombreOrApellido(String term);

    Iterable<Alumno> findAllById(Iterable<Long> ids);

    void eliminarCursoAlumnoPorId(@PathVariable Long id);

}

