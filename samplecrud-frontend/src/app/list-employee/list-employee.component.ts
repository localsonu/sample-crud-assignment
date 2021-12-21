import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Employee } from 'src/employee';
import { EmployeeserviceService } from 'src/employeeservice.service';

@Component({
  selector: 'app-list-employee',
  templateUrl: './list-employee.component.html',
  styleUrls: ['./list-employee.component.css']
})
export class ListEmployeeComponent implements OnInit {

  employees?: Employee[];
  result?: string[] = [];
  config: any;
  collection = { employees: [] };
  constructor(private employeeService: EmployeeserviceService,
    private router: Router) 
     {  
      this.config = {
        itemsPerPage: 5,
        currentPage: 1,
        totalItems:60
      };
    }

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
  this.employeeService.getEmployeesList().subscribe({next:(data)=>{
    this.employees=data;
    console.log(data);
  },
  error:(e)=>console.error(e)
});
  }

  deleteEmployee(id: any) {
    this.employeeService.deleteEmployee(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
  }

  employeeDetails(id: any){
    this.router.navigate(['details', id]);
  }
  updateEmployee(id: any){
    this.router.navigate(['update', id]);
  }
  pageChanged(event: any){
    this.config.currentPage = event;
  }

}
