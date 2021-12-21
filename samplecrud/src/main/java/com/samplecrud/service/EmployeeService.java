package com.samplecrud.service;

import com.samplecrud.exception.ResourceNotFoundException;
import com.samplecrud.model.Department;
import com.samplecrud.model.Employee;
import com.samplecrud.respository.DepartmentRepository;
import com.samplecrud.respository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public Employee save(Employee employee) throws ResourceNotFoundException {
        if (employee.getEmployeeID() != null) {
            throw new EntityExistsException("There is already existing entity with such ID in the database.");
        }
       Department department1=  departmentRepository.findByDepartment_Name(employee.getDepartmentName());
        if(department1== null){
            throw new ResourceNotFoundException("Employee not found for this id :: " + employee.getDepartmentName());
        }
        employee.setDepartment(department1);

        return employeeRepository.save(employee);
    }
}
