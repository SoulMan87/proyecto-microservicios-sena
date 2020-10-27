package com.soulrebel.microservicios.commons.alumnos.models.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

@Data
@Entity
@Table(name = "alumnos")
@NoArgsConstructor
@AllArgsConstructor
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String nombre;
    @NotEmpty
    private String apellido;
    @NotEmpty
    @Email
    private String email;

    @Column(name = "create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @Lob
    @JsonIgnore
    private byte[] foto;

    @PrePersist
    public void prePersist() {
        this.createAt = new Date();
    }

    public Integer getFotoHashCode() {
        return (this.foto != null) ? Arrays.hashCode(this.foto) : null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Alumno)) return false;
        Alumno alumno = (Alumno) o;
        return Objects.equals(id, alumno.id) &&
                Objects.equals(nombre, alumno.nombre) &&
                Objects.equals(apellido, alumno.apellido) &&
                Objects.equals(email, alumno.email) &&
                Objects.equals(createAt, alumno.createAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, apellido, email, foto, createAt);
    }
}
