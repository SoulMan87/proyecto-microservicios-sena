package com.soulrebel.microservicios.app.alumnos.models.service;

import com.soulrebel.microservicios.commons.alumnos.models.entity.Alumno;
import com.soulrebel.microservicios.commons.services.CommonService;

import java.util.List;


public interface AlumnoService extends CommonService<Alumno> {

    public List<Alumno> findByNombreOrApellido(String term);


}

