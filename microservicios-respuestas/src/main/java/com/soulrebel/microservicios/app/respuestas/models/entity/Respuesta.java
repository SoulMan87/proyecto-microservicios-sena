package com.soulrebel.microservicios.app.respuestas.models.entity;

import com.soulrebel.microservicios.commons.alumnos.models.entity.Alumno;
import com.soulrebel.microservicios.commons.examenes.models.entity.Pregunta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@AllArgsConstructor
@Document(collection = "respuestas")
@SuperBuilder
public class Respuesta {

    @Id
    private String id;

    private String texto;

    //@Transient
    private Pregunta pregunta;

    //@Transient
    private Alumno alumno;

    private Long alumnoId;

    private Long preguntaId;
}
