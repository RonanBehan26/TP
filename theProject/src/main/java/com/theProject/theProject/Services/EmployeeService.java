package com.theProject.theProject.Services;

import com.theProject.theProject.Model.EmployeeDTO;
import com.theProject.theProject.ValueObjects.ResponseTemplateVO;

import java.util.List;

public interface EmployeeService {
    EmployeeDTO createEmployee(EmployeeDTO employee);

    List<EmployeeDTO> getAllEmployees();

    boolean deleteEmployee(Long id);

    ResponseTemplateVO getEmployeeById(Long id);

    EmployeeDTO updateEmployee(Long id, EmployeeDTO employee);
}
