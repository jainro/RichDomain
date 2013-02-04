package com.rohit;

import com.rohit.config.DataConfig;
import com.rohit.domain.Employee;
import com.rohit.repository.PersistenceRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataConfig.class})
public class DatabaseTest {

    @Autowired
    PersistenceRepository<Employee> employeeRepository;

    @Test
    @Transactional
    public void test() {
        final Employee employee = new Employee("rohit", "jain");
        employeeRepository.save(employee);

        assertThat(employee.getId(), is(1L)) ;
        assertThat(employee.getVersion(), is(1)) ;
    }
}
