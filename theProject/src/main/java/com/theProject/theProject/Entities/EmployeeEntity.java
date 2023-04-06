package com.theProject.theProject.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity //works with JPA to save the data in the DB
@Data //getters and setters, and all methods added from Lombok
@Table(name = "employee")
@NoArgsConstructor
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name= "employee_id", length = 45)
    private Long employeeId;

    @Column(name= "employee_firstName", length = 250)
    private String employeeFirstName;

    @Column(name= "employee_lastName", length = 250)
    private String employeeLastName;

    @Column(name= "employeeEmail", length = 250)
    private String employeeEmail;

    @Column(name= "employeePassword", length = 250)
    private String employeePassword;

    @Column(name= "employee_address", length = 250)
    private String employeeAddress;

    @Column(name= "employee_phoneNumber", length = 250)
    private String employeePhoneNumber;

    @Column(name= "salary_PA", length = 250)
    private String salaryPA;

    @Column(name= "b2b", length = 250)
    private  String b2b;

    @Column(name= "team", length = 250)
    private String team;

    @Column(name= "department_Id", length = 45)
    private Long departmentId;

    public EmployeeEntity(String team, Long employeeId, String b2b, String employeeFirstName, String employeeAddress, String employeePhoneNumber,
                          Long departmentId, String salaryPA, String employeeLastName, String employeeEmail, String employeePassword) {
        this.team = team;
        this.employeeId = employeeId;
        this.b2b = b2b;
        this.employeeFirstName = employeeFirstName;
        this.employeeAddress = employeeAddress;
        this.employeePhoneNumber = employeePhoneNumber;
        this.departmentId = departmentId;
        this.salaryPA = salaryPA;
        this.employeeLastName = employeeLastName;
        this.employeeEmail = employeeEmail;
        this.employeePassword = employeePassword;
    }
}
