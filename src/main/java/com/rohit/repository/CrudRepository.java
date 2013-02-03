package com.rohit.repository;

import com.rohit.domain.PersistentEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CrudRepository <T extends PersistentEntity> {

    @Transactional(propagation = Propagation.MANDATORY)
    T save(T t);

    List<T> findAll();

    List<T> save(Iterable<T> entities);

    void flush();


    T saveAndFlush(T entity);
}
