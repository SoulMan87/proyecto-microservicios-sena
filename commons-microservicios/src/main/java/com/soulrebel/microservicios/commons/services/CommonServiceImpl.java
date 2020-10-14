package com.soulrebel.microservicios.commons.services;



import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;


public class CommonServiceImpl<E, R extends CrudRepository<E, Long>> implements CommonService<E>{

    protected final R repository;

    public CommonServiceImpl(R repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<E> findAllService() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<E> findByIdService(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public E saveService(E entity) {
        return repository.save(entity);
    }

    @Override
    @Transactional
    public void deleteService(Long id) {
        repository.deleteById(id);
    }
}
