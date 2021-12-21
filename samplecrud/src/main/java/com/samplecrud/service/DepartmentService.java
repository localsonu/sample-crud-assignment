package com.samplecrud.service;

import com.samplecrud.exception.ResourceNotFoundException;
import com.samplecrud.model.Department;
import com.samplecrud.model.Employee;
import com.samplecrud.respository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;
@Service
public class DepartmentService {
    private DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Department save(Department department) {
        if (department.getId() != null && departmentRepository.findByDepartment_Name(department.getDepartment_Name())!=null) {
            throw new EntityExistsException("There is already existing entity with such ID in the database.");
        }

        return departmentRepository.save(department);
    }

    public Department update(Department department) throws ResourceNotFoundException {
        if (department.getId() != null && departmentRepository.findByDepartment_Name(department.getDepartment_Name())!=null) {
            throw new ResourceNotFoundException("There is already existing entity with such ID in the database.");
        }
        return departmentRepository.save(department);
    }

    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    public void delete(Department depart) {
        departmentRepository.delete(depart);
    }

    public Department findById(Integer id) throws ResourceNotFoundException {
       return departmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Department not found for this id :: " + id));
    }
}
