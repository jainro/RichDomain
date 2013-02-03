package com.rohit.domain;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

//@Repository
public class EmployeeRepository implements IEmployeeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(propagation = Propagation.MANDATORY)
    public Employee save(Employee employee) {
        if (employee.getId() == null) {
            entityManager.persist(employee);
        } else {
            entityManager.merge(employee);
        }
        entityManager.flush();
        return employee;
    }
}
