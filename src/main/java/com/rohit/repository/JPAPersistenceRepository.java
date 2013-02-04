package com.rohit.repository;

import com.rohit.domain.PersistentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.support.SharedEntityManagerBean;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class JPAPersistenceRepository<T extends PersistentEntity> implements PersistenceRepository<T> {

    private final SharedEntityManagerBean sharedEntityManagerBean;

    @Autowired
    public JPAPersistenceRepository(SharedEntityManagerBean sharedEntityManagerBean) {
        this.sharedEntityManagerBean = sharedEntityManagerBean;
    }

    public T save(T t) {
        if(null == t.getId()){
            currentEntityManager().persist(t);
        } else{
            currentEntityManager().merge(t);
        }
        return t;
    }

    public void flush() {
       currentEntityManager().flush();
    }

    public T saveAndFlush(T t) {
       save(t) ;
       flush();
       return t;
    }

    private EntityManager currentEntityManager() {
        return sharedEntityManagerBean.getObject();
    }
}
