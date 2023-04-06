package com.theProject.theProject.ValueObjects;

import com.theProject.theProject.Model.EmployeeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTemplateVO {

    private EmployeeDTO employee;
    private Department department;
}
