package com.samplecrud.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table (name = "employee")
@Data
public class Employee {

    @Id
    @Column(name = "employee_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer employeeID;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phone_number;

    @Column(name = "hire_date")
    private String hire_date;

    @Column(name = "salary")
    private Integer salary;

    @Column(name = "manager_id")
    private Integer manager_id;

    @Transient
    private String departmentName;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public Employee(int employeeID, String firstName, String lastName, String email, String phone_number, String hire_date, Integer salary, Integer manager_id, Department department) {
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