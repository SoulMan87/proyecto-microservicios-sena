package com.soulrebel.microservicios.commons.examenes.models.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
@Table(name = "asignaturas")
public class Asignatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @JsonIgnoreProperties(value = {"hijos"})
    @ManyToOne(fetch = FetchType.LAZY)
    private Asignatura padre;

    @JsonIgnoreProperties(value = {"padre"}, allowSetters = true)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "padre", cascade = CascadeType.ALL)
    private List<Asignatura> hijos;
}
