package com.g.miniproject.service;

import com.g.miniproject.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> fetchEmployeeList();

    Employee fetchEmployee(Long id);

    Employee addEmployee(Employee employee);



    Employee updateEmployee(Employee employee, Long id);

    void deleteEmployee(Long id);


}
