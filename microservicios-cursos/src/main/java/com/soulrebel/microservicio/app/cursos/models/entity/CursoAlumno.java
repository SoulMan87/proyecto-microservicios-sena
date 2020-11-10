package com.soulrebel.microservicio.app.cursos.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cursos_alumnos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CursoAlumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "alumno_id", unique = true)
    private Long alumnoId;

    @JsonIgnoreProperties(value = {"cursoAlumnos"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CursoAlumno)) return false;
        CursoAlumno that = (CursoAlumno) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(alumnoId, that.alumnoId) &&
                Objects.equals(curso, that.curso);
    }

}
