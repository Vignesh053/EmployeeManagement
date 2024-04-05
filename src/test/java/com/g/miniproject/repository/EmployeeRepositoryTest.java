package com.g.miniproject.repository;

import com.g.miniproject.entity.Employee;
import com.g.miniproject.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@TestPropertySource(properties = {
        "spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect"
})
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee;

    @BeforeEach
    public void setup(){
        employee = Employee.builder()
                .firstname("john")
                .lastname("doe")
                .email("johndoe@gmail.com")
                .build();
    }

    @DisplayName("Test when an employee is saved")
    @Test
    public void givenEmployee_whenSaved_returnSavedEmployee(){
        Employee savedEmployee = employeeRepository.save(employee);
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isGreaterThan(0);
    }


    @DisplayName("Test when checking if an email exists")
    @Test
    public void givenEmail_whenExistsByEmail_returnBoolean(){
        employeeRepository.save(employee);
        assertTrue(employeeRepository.existsByEmail(employee.getEmail()));
        assertFalse(employeeRepository.existsByEmail("test@hotmail.com"));
    }

    @DisplayName("Test when fetching all employees")
    @Test
    public void givenEmployeeList_whenfind_returnEmployeeList(){
        Employee employee2 = Employee.builder()
                .firstname("peter")
                .lastname("griffin")
                .email("familyguy@gmail.com")
                .build();

        employeeRepository.save(employee);
        employeeRepository.save(employee2);

        List<Employee> employeeList = employeeRepository.findAll();

        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);

    }

    @DisplayName("Test when finding employee by id")
    @Test
    public void givenId_whenFind_returnEmployee(){
        Employee savedEmployee = employeeRepository.save(employee);

        Optional<Employee> foundEmployee = employeeRepository.findById(savedEmployee.getId());

        assertThat(foundEmployee).isNotNull();
        assertThat(foundEmployee.get()).isEqualTo(savedEmployee);

    }
}
