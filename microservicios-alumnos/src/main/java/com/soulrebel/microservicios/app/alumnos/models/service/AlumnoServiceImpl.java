package com.soulrebel.microservicios.app.alumnos.models.service;

import com.soulrebel.microservicios.app.alumnos.models.entity.Alumno;
import com.soulrebel.microservicios.app.alumnos.models.repository.AlumnoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@RequiredArgsConstructor
@Service("alumnoService")
public class AlumnoServiceImpl implements AlumnoService {

    private final AlumnoRepository repository;

    @Override
    public Iterable<Alumno> findAllService() {
        return repository.findAll();
    }

    @Override
    public Optional<Alumno> findByIdService(Long id) {
        return repository.findById(id);
    }

    @Override
    public Alumno saveAlumnoService(Alumno alumno) {
        return repository.save(alumno);
    }

    @Override
    public void deleteAlumnoService(Long id) {
        repository.deleteById(id);
    }
}
