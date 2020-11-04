package com.soulrebel.microservicio.app.cursos.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.soulrebel.microservicios.commons.alumnos.models.entity.Alumno;
import com.soulrebel.microservicios.commons.examenes.models.entity.Examen;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@Table(name = "cursos")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String nombre;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Alumno> alumnos;

    //@ManyToMany(fetch = FetchType.LAZY)
    @Transient
    private List<Examen> examenes;

    @Column(name = "create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @JsonIgnoreProperties(value = {"curso"}, allowSetters = true)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CursoAlumno> cursoAlumnos;

    @PrePersist
    public void prePersist() {
        this.createAt = new Date();
    }

    public Curso() {
        this.alumnos = new ArrayList<>();
        this.examenes = new ArrayList<>();
        this.cursoAlumnos = new ArrayList<>();
    }

    public void addAlumno(Alumno alumno) {
        this.alumnos.add(alumno);
    }

    public void removeAlumno(Alumno alumno) {
        this.alumnos.remove(alumno);
    }

    public void addExamen(Examen examen) {
        this.examenes.add(examen);
    }

    public void removeExamen(Examen examen) {
        this.examenes.remove(examen);
    }

    public void addCursoAlumno(CursoAlumno cursoAlumno) {
        this.cursoAlumnos.add(cursoAlumno);
    }

    public void removeCursoAlumno(CursoAlumno cursoAlumno) {
        this.cursoAlumnos.remove(cursoAlumno);
    }
}
