package com.soulrebel.microservicios.app.respuestas.models.entity;

import com.soulrebel.microservicios.commons.alumnos.models.entity.Alumno;
import com.soulrebel.microservicios.commons.examenes.models.entity.Pregunta;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Entity
@Table(name = "respuestas")
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String texto;

    @OneToOne(fetch = FetchType.LAZY)
    private Pregunta pregunta;

    @Transient
    private Alumno alumno;

    @Column(name = "alumno_id")
    private Long alumnoId;
}
