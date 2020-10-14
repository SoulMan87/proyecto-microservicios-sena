package com.soulrebel.microservicios.app.alumnos.models.repository;

import com.soulrebel.microservicios.commons.alumnos.models.entity.Alumno;
import org.springframework.data.repository.CrudRepository;

public interface AlumnoRepository extends CrudRepository<Alumno, Long> {
}
