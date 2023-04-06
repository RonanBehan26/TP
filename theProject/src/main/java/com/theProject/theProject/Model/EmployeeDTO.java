package com.theProject.theProject.Model;

import jakarta.persistence.Column;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class EmployeeDTO {

    //this class was just called Employee before, until recently

    private Long employeeId;
    private  String employeeFirstName;
    private  String employeeLastName;

    private  String employeeEmail;

    private  String employeePassword;

    private  String employeeAddress;

    private  String employeePhoneNumber;

    private  String salaryPA;

    private  String b2b;

    private String team;

    private Long departmentId;

}
