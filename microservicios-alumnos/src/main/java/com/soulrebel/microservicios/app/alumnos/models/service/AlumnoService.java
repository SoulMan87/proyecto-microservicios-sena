package com.soulrebel.microservicios.app.alumnos.models.service;

import com.soulrebel.microservicios.app.alumnos.models.entity.Alumno;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface AlumnoService {

    Iterable<Alumno> findAllService();

    Optional<Alumno> findByIdService(Long id);

    Alumno saveAlumnoService(Alumno alumno);

    void deleteAlumnoService(Long id);
}

