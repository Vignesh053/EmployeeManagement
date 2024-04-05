package com.g.miniproject.service;


import com.g.miniproject.entity.Employee;
import com.g.miniproject.repository.EmployeeRepository;
import com.g.miniproject.service.serviceimpl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeServiceimpl;

    private Employee employee;

    @BeforeEach
    public void setup(){
        employee = Employee.builder()
                .firstname("john")
                .lastname("doe")
                .email("johndoe@gmail.com")
                .build();
    }

    @DisplayName("Test when fetch a list of employees")
    @Test
    public void givenEmployeelist_whenFetchEmployeeList_returnEmployeeList(){
        Employee employee1 = Employee.builder()
                .firstname("peter")
                .lastname("parker")
                .email("peter@gmail.com")
                .build();

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);
        employeeList.add(employee1);

        when(employeeRepository.findAll()).thenReturn(employeeList);

        List<Employee> result = employeeServiceimpl.fetchEmployeeList();

        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(2);


    }

    @DisplayName("Test when fetch employee by Id")
    @Test
    public void givenEmployeeId_whenFetchEmployee_returnEmployee(){
        Long id = 1L;

        when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));

        Employee result = employeeServiceimpl.fetchEmployee(id);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(employee);


    }


    @DisplayName("Test When adding new employee")
    @Test
    public void givenEmployee_whenAddEmployee_returnAddedEmployee(){
        when(employeeRepository.existsByEmail(employee.getEmail())).thenReturn(false);
        when(employeeRepository.save(employee)).thenReturn(employee);

        Employee result = employeeServiceimpl.addEmployee(employee);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(employee);
    }


    @DisplayName("Test when updating an existing employee")
    @Test
    public  void givenEmployee_whenUpdate_returnUpdatedEmployee(){
        Long id = 1L;
        Employee updatedEmployee = new Employee(null, "john", "shane", "shane@gmail.com");

        when(employeeRepository.findById(id)).thenReturn(Optional.ofNullable(employee));
        when(employeeRepository.save(updatedEmployee)).thenReturn(updatedEmployee);

        Employee result = employeeServiceimpl.updateEmployee(updatedEmployee, id);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(updatedEmployee);
    }

    @DisplayName("Test when deleting an Employee")
    @Test
    public void givenEmployeeId_whenDeleteEmployee_doNotThrowException() {
        long id = 1L;
        doNothing().when(employeeRepository).deleteById(id);

        employeeServiceimpl.deleteEmployee(id);
        verify(employeeRepository, times(1)).deleteById(id);
    }
}
