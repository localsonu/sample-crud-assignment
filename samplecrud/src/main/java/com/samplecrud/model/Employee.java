package com.samplecrud.model;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table (name = "employee")
@Data
public class Employee {

    @Id
    @Column(name = "employee_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer employeeID;

    @Column(name = "first_name")
    @NotNull
    @Size(min=2,message = "first name should have 2 character")
    private String firstName;

    @Column(name = "last_name")
    @NotNull
    @Size(min=2,message = "last name should have 2 character")
    private String lastName;

    @Column(name = "email")
    @NotNull
    private String email;

    @Column(name = "phone_number")
    @NotNull
    private Integer phone_number;

    @Column(name = "hire_date")
    private Date hire_date;

    @Column(name = "salary")
    @Range(min = 1,max = 1000000)
    private Integer salary;

    @Column(name = "manager_id")
    private Integer manager_id;

    @Transient
    private String departmentName;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public Employee(int employeeID, String firstName, String lastName, String email, Integer phone_number, Date hire_date, Integer salary, Integer manager_id, Department department) {
        this.employeeID = employeeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone_number = phone_number;
        this.hire_date = hire_date;
        this.salary = salary;
        this.manager_id = manager_id;
        this.department = department;
    }

    public Employee() {

    }
}