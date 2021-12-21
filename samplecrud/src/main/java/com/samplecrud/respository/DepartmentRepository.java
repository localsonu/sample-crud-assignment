package com.samplecrud.respository;

import com.samplecrud.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
  @Query("SELECT d FROM Department d WHERE d.department_Name=?1")
  Department findByDepartment_Name(String department_Name);
}