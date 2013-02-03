package com.rohit.domain;


import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface IEmployeeRepository {
    @Transactional(propagation = Propagation.MANDATORY)
    Employee save(Employee employee);
}
