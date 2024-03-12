package com.g.miniproject.controller;

import com.g.miniproject.entity.Employee;
import com.g.miniproject.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("employee")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;

    @GetMapping("/fetchlist")
    public ResponseEntity<List<Employee>> fetchList(){
        return new ResponseEntity<>(employeeService.fetchEmployeeList(), HttpStatus.OK);
    }

    @GetMapping("/fetch/{id}")
    public ResponseEntity<Employee> fetchEmployee(@PathVariable Long id){
        return new ResponseEntity<>(employeeService.fetchEmployee(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
        return new ResponseEntity<>(employeeService.addEmployee(employee), HttpStatus.CREATED);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee , @PathVariable Long id){
        return new ResponseEntity<>(employeeService.updateEmployee(employee, id), HttpStatus.OK);
    }

    @DeleteMapping("/del/{id}")
    public void deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
    }
}
