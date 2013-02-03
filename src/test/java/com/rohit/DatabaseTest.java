package com.rohit;

import com.rohit.config.DataConfig;
import com.rohit.domain.Employee;
import com.rohit.domain.IEmployeeRepository;
import com.rohit.repository.CrudRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.enterprise.inject.spi.Bean;
import javax.persistence.EntityManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataConfig.class})
public class DatabaseTest {

   /* @Autowired
    IEmployeeRepository employeeRepository;*/

      @Autowired
     CrudRepository<Employee> employeeRepository;

    @Test
    @Transactional
    public void test() {
        final Employee employee = new Employee("rohit", "jain");
        employeeRepository.save(employee);

        System.out.println(employee.getId());
        System.out.println(employee.getVersion());

    }
}
