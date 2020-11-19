package com.soulrebel.microservicios.app.alumnos.models.service;

import com.soulrebel.microservicios.app.alumnos.client.CursoFeignClient;
import com.soulrebel.microservicios.app.alumnos.models.repository.AlumnoRepository;
import com.soulrebel.microservicios.commons.alumnos.models.entity.Alumno;
import com.soulrebel.microservicios.commons.services.CommonServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("alumnoService")
public class AlumnoServiceImpl extends CommonServiceImpl<Alumno, AlumnoRepository> implements AlumnoService {
    public AlumnoServiceImpl(AlumnoRepository repository, CursoFeignClient client) {
        super(repository);
        this.client = client;
    }

    private final CursoFeignClient client;

    @Override
    @Transactional(readOnly = true)
    public List<Alumno> findByNombreOrApellido(String term) {
        return repository.findByNombreOrApellido(term);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Alumno> findAllById(Iterable<Long> ids) {
        return repository.findAllById(ids);
    }

    @Override
    public void eliminarCursoAlumnoPorId(Long id) {
        client.eliminarCursoAlumnoPorId(id);
    }

    @Override
    @Transactional
    public void deleteService(Long id) {
        super.deleteService(id);
        this.eliminarCursoAlumnoPorId(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Alumno> findAllService() {
        return repository.findAllByOrderByIdAsc();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Alumno> findAllPage(Pageable pageable) {
        return repository.findAllByOrderByIdAsc(pageable);
    }
}


