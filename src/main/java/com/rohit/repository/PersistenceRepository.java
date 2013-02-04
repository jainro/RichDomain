package com.rohit.repository;

import com.rohit.domain.PersistentEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface PersistenceRepository<T extends PersistentEntity> {

    @Transactional(propagation = Propagation.MANDATORY)
    T save(T t);

    @Transactional(propagation = Propagation.MANDATORY)
    void flush();

    @Transactional(propagation = Propagation.MANDATORY)
    T saveAndFlush(T entity);
}
