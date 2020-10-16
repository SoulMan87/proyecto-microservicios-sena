package com.soulrebel.microservicios.app.examenes.models.repository;

import com.soulrebel.microservicios.app.examenes.models.entity.Examen;
import org.springframework.data.repository.CrudRepository;

public interface ExamenRepository  extends CrudRepository<Examen, Long> {
}
