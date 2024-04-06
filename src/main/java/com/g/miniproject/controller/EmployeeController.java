package com.g.miniproject.controller;

import com.g.miniproject.dto.LoginDto;
import com.g.miniproject.entity.Employee;
import com.g.miniproject.security.JwtTokenUtil;
import com.g.miniproject.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("employee")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;

    private AuthenticationManager authenticationManager;

    private JwtTokenUtil jwtTokenUtil;

    //get all employee's list
    @GetMapping("/fetchlist")
    public ResponseEntity<List<Employee>> fetchList(){
        return new ResponseEntity<>(employeeService.fetchEmployeeList(), HttpStatus.OK);
    }

    //get one employee using his id
    @GetMapping("/fetch/{id}")
    public ResponseEntity<Employee> fetchEmployee(@PathVariable Long id){
        return new ResponseEntity<>(employeeService.fetchEmployee(id), HttpStatus.OK);
    }

    //add new employee by accepting employee details
    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
        return new ResponseEntity<>(employeeService.addEmployee(employee), HttpStatus.CREATED);
    }

    //update an existing employee by finding by id and update the new details
    @PostMapping("/update/{id}")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee , @PathVariable Long id){
        return new ResponseEntity<>(employeeService.updateEmployee(employee, id), HttpStatus.OK);
    }

    //delete employee by id
    @DeleteMapping("/del/{id}")
    public void deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
    }
}
