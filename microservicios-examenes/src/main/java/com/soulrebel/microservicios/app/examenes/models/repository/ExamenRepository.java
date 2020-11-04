package com.soulrebel.microservicios.app.examenes.models.repository;

import com.soulrebel.microservicios.commons.examenes.models.entity.Examen;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ExamenRepository  extends PagingAndSortingRepository<Examen, Long> {

    @Query("select e from Examen e where  e.nombre like %?1%")
    List<Examen> findByNombre(String term);
}
