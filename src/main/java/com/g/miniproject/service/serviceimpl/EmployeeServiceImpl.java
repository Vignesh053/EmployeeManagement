package com.g.miniproject.service.serviceimpl;

import com.g.miniproject.entity.Employee;
import com.g.miniproject.exception.EmailAlreadyExistsException;

import com.g.miniproject.exception.ResourceNotFoundException;
import com.g.miniproject.repository.EmployeeRepository;
import com.g.miniproject.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    @Override
    public List<Employee> fetchEmployeeList() {
        List<Employee> employees = employeeRepository.findAll();
        return employees;
    }

    @Override
    public Employee fetchEmployee(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", String.valueOf(id)));
    }

    @Override
    public Employee addEmployee(Employee employee) {
        if (employeeRepository.existsByEmail(employee.getEmail())) {
            System.out.println(employeeRepository.existsByEmail(employee.getEmail()));
            throw new EmailAlreadyExistsException(employee.getEmail());
        } else {


        }

        return employeeRepository.save(employee);
    }


    @Override
    public Employee updateEmployee(Employee employee, Long id) {
        Employee oldEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", String.valueOf(id)));

        oldEmployee.setFirstname(employee.getFirstname());
        oldEmployee.setLastname(employee.getLastname());
        oldEmployee.setEmail(employee.getEmail());
        return employeeRepository.save(oldEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
