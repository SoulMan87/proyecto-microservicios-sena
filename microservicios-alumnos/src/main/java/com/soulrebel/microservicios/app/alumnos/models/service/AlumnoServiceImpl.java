package com.soulrebel.microservicios.app.alumnos.models.service;

import com.soulrebel.microservicios.commons.alumnos.models.entity.Alumno;
import com.soulrebel.microservicios.app.alumnos.models.repository.AlumnoRepository;
import com.soulrebel.microservicios.commons.services.CommonServiceImpl;
import org.springframework.stereotype.Service;


@Service("alumnoService")
public class AlumnoServiceImpl extends CommonServiceImpl<Alumno, AlumnoRepository> implements AlumnoService {
    public AlumnoServiceImpl(AlumnoRepository repository) {
        super(repository);
    }
}


