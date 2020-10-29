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

    @ManyToOne(fetch = FetchType.LAZY)
    private Pregunta pregunta;

    @OneToOne(fetch = FetchType.LAZY)
    private Alumno alumno;
}
