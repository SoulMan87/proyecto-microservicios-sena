package com.soulrebel.microservicio.app.cursos.models.entity;

import com.soulrebel.microservicios.commons.alumnos.models.entity.Alumno;
import lombok.*;

import javax.persistence.*;
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
    private String nombre;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Alumno> alumnos;

    @Column(name = "create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @PrePersist
    public void prePersist() {
        this.createAt = new Date();
    }

    public Curso(){
        this.alumnos = new ArrayList<>();
    }

    public void addAlumno(Alumno alumno){
        this.alumnos.add(alumno);
    }
    public void removeAlumno(Alumno alumno){
        this.alumnos.remove(alumno);
    }

}
