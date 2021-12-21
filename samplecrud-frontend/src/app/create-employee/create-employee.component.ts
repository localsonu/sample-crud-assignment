import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Employee } from 'src/employee';
import { EmployeeserviceService } from 'src/employeeservice.service';

@Component({
  selector: 'app-create-employee',
  templateUrl: './create-employee.component.html',
  styleUrls: ['./create-employee.component.css']
})
export class CreateEmployeeComponent implements OnInit {

  employee: Employee = new Employee();
  submitted = false;
  

  constructor(private employeeService: EmployeeserviceService,
    private router: Router) { }

  ngOnInit() {
  }
  form = new FormGroup({
    firstName: new FormControl('', [Validators.required, Validators.minLength(2)]),
    lastName: new FormControl('', [Validators.required, Validators.minLength(2)]),
    email: new FormControl('', [Validators.required, Validators.email]),
    salary: new FormControl('', [Validators.required,   Validators.pattern("^[1-9]*$"),]),
    date: new FormControl('', [Validators.required]),
    departmentName: new FormControl('', [Validators.required]),
    phone_number: new FormControl('', [Validators.required,   Validators.pattern('[- +()0-9 {12}]+'),]),

  });

  newEmployee(): void {
    this.submitted = false;
    this.employee = new Employee();
  }

  save() {
    this.employeeService.createEmployee(this.employee)
      .subscribe(data => console.log(data), error => console.log(error));
    this.employee = new Employee();
    this.gotoList();
  }

  onSubmit() {
    if (this.form.invalid) {
      return;
  }
    this.submitted = true;
    this.save();    
  }

  gotoList() {
    this.router.navigate(['/employees']);
  }
  get f(){
    return this.form.controls;
  }
}
