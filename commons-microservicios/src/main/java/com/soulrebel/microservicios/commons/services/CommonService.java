package com.soulrebel.microservicios.commons.services;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface CommonsService<E> {

    Iterable<E> findAllService();

    Optional<E> findByIdService(Long id);

    E saveService(E entity);

    void deleteService(Long id);
}

