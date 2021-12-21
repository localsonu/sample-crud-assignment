import { Department } from "./department";

export class Employee {
    employeeID?: number;
    firstName?: string;
    lastName?: string;
    email?: string;
    phone_number?:string;
    salary?:string;
    departmentName?:string;
    hire_date?:Date;
    active?: boolean;
    department?:Department;
}
