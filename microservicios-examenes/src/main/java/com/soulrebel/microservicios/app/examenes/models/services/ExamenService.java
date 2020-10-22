package com.soulrebel.microservicios.app.examenes.models.services;


import com.soulrebel.microservicios.commons.examenes.models.entity.Examen;
import com.soulrebel.microservicios.commons.services.CommonService;

import java.util.List;

public interface ExamenService extends CommonService<Examen> {

    public List<Examen> findByNombre(String term);
}
