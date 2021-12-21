package com.samplecrud.controller;

import com.samplecrud.exception.ResourceNotFoundException;
import com.samplecrud.model.Department;
import com.samplecrud.respository.DepartmentRepository;
import com.samplecrud.service.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
@Api(tags = "Crud Operation")
public class DepartmentController {
    static final Logger logger  = LogManager.getLogger(DepartmentController.class.getName());

    @Autowired
    private DepartmentService departmentService;

    @ApiOperation(value = "Get All department")
    @GetMapping("/departments")
    public List<Department> getAllDepartments() {
        return departmentService.findAll();
    }

    @ApiOperation(value = "Get department By id")
    @GetMapping("/departments/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable(value = "id") Integer departmentId)
            throws ResourceNotFoundException {
        if (departmentId == null) {
            return new ResponseEntity<Department>(HttpStatus.BAD_REQUEST);
        }
        Department department = departmentService.findById(departmentId);
        return ResponseEntity.ok().body(department);
    }

    @ApiOperation(value = "Add new department")
    @PostMapping("/departments")
    public Department createDepartment(@RequestBody Department department) {
        return departmentService.save(department);
    }

    @ApiOperation(value = "Delete department")
    @DeleteMapping("/departments/{id}")
    public Map<String, Boolean> deleteDepartment(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {
        Department department = departmentService.findById(id);
        departmentService.delete(department);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @ApiOperation(value = "update new department")
    @PutMapping("/departments")
    public ResponseEntity<Department> updateDepartment(@RequestBody Department department) {
        if (department.getId() == null) {
            return new ResponseEntity<Department>(HttpStatus.BAD_REQUEST);
        }
        try {
            Department result = departmentService.update(department);
            return ResponseEntity.ok().body(result);
        } catch (EntityNotFoundException | ResourceNotFoundException e) {
            return new ResponseEntity<Department>(HttpStatus.NOT_FOUND);
        }
    }
}
