package com.soulrebel.microservicio.app.cursos.models.repository;

import com.soulrebel.microservicio.app.cursos.models.entity.Curso;
import org.springframework.data.repository.CrudRepository;

public interface CursoRepository extends CrudRepository<Curso, Long> {
}
