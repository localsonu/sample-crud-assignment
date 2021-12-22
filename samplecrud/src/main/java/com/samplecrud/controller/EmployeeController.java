package com.samplecrud.controller;

import com.samplecrud.exception.ResourceNotFoundException;
import com.samplecrud.model.Department;
import com.samplecrud.model.Employee;
import com.samplecrud.respository.EmployeeRepository;
import com.samplecrud.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import javax.validation.Valid;


import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
@Api(tags = "Crud Operation")
public class EmployeeController {
    static final Logger logger  = LogManager.getLogger(EmployeeController.class.getName());

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    @ApiOperation(value = "Get All Employee")
    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @ApiOperation(value = "Get Employee By id")
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Integer employeeId)
            throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
        return ResponseEntity.ok().body(employee);
    }

    @ApiOperation(value = "Add new Employee")
    @PostMapping("/employees")
    public Employee createEmployee(@Valid @RequestBody Employee employee) throws ResourceNotFoundException {
        return employeeService.save(employee);
    }

    @ApiOperation(value = "Delete Employee")
    @DeleteMapping("/employees/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {
        Long l = new Long(id);
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + id));

        employeeRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
    @ApiOperation(value = "update employee")
    @PutMapping("/employee/{id}")
    public ResponseEntity<Employee> updateDepartment( @PathVariable(value = "id") Integer id, @RequestBody Employee employee) {
        if (employee.getEmployeeID() == null) {
            return new ResponseEntity<Employee>(HttpStatus.BAD_REQUEST);
        }
        try {
            Employee result = employeeService.update(employee, id);
            return ResponseEntity.ok().body(result);
        } catch (EntityNotFoundException | ResourceNotFoundException e) {
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }
    }
}
