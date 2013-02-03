package com.rohit.repository;

import com.rohit.domain.PersistentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.support.SharedEntityManagerBean;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class JPACrudRepository<T extends PersistentEntity> implements CrudRepository<T> {

    private final SharedEntityManagerBean sharedEntityManagerBean;

    @Autowired
    public JPACrudRepository(SharedEntityManagerBean sharedEntityManagerBean) {
        this.sharedEntityManagerBean = sharedEntityManagerBean;
    }

    public T save(T t) {
        if(null == t.getId()){
            currentEntityManager().persist(t);
        } else{
            currentEntityManager().merge(t);
        }
        currentEntityManager().flush();
        currentEntityManager().flush();
        return t;
    }

    public List<T> findAll() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<T> save(Iterable<T> entities) {
        for (T entity : entities) {
            save(entity);
        }
        return null;
    }

    public void flush() {
       currentEntityManager().flush();
    }

    public T saveAndFlush(T t) {
        if(null == t.getId()){
            currentEntityManager().persist(t);
        } else{
            currentEntityManager().merge(t);
        }
        currentEntityManager().flush();
        return t;
    }

    private EntityManager currentEntityManager() {
        return sharedEntityManagerBean.getObject();
    }
}
