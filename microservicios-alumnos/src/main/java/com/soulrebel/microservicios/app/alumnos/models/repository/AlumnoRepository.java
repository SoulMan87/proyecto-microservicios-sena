package com.soulrebel.microservicios.app.alumnos.models.repository;

import com.soulrebel.microservicios.commons.alumnos.models.entity.Alumno;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface AlumnoRepository extends PagingAndSortingRepository<Alumno, Long> {

    @Query("select a from Alumno a where upper(a.nombre) like upper(concat('%', ?1, '%')) or upper(a.apellido) like upper(concat('%', ?1, '%'))")
    public List<Alumno> findByNombreOrApellido(String term);
}
