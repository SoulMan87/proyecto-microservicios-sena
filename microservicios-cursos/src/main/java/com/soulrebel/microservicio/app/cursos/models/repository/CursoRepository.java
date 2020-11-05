package com.soulrebel.microservicio.app.cursos.models.repository;

import com.soulrebel.microservicio.app.cursos.models.entity.Curso;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CursoRepository extends PagingAndSortingRepository<Curso, Long> {

    @Query("select c from Curso c join fetch c.cursoAlumnos a where a.alumnoId=?1")
    public Curso findCursoByAlumnosId(Long id);
}
