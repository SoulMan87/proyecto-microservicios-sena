package com.soulrebel.microservicios.commons.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface CommonService<E> {

    Iterable<E> findAllService();

    Page<E> findAllPage(Pageable pageable);

    Optional<E> findByIdService(Long id);

    E saveService(E entity);

    void deleteService(Long id);
}

