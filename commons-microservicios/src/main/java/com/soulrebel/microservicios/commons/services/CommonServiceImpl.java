package com.soulrebel.microservicios.commons.services;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service("commons")
public class AlumnoServiceImpl<E, R extends CrudRepository<E, Long>> implements CommonsService<E>{

    private final R repository;

    @Override
    public Iterable<E> findAllService() {
        return repository.findAll();
    }

    @Override
    public Optional<E> findByIdService(Long id) {
        return repository.findById(id);
    }

    @Override
    public E saveAlumnoService(E entity) {
        return repository.save(entity);
    }

    @Override
    public void deleteAlumnoService(Long id) {
        repository.deleteById(id);
    }
}
