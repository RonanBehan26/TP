package com.theProject.theProject.Controllers;

import com.theProject.theProject.Model.EmployeeDTO;
import com.theProject.theProject.Services.EmployeeService;
import com.theProject.theProject.ValueObjects.ResponseTemplateVO;
import jakarta.ws.rs.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1")
@CrossOrigin(origins = "*") //have a config class, which works globally as get works
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping(value = "/employee")
    @ResponseBody
    public EmployeeDTO createEmployee(EmployeeDTO employee){

        return employeeService.createEmployee(employee);
    }
    //@RequestBody for postman I believe, all null without

    @GetMapping("/employee")
    public List<EmployeeDTO> getEmployees(){
        return employeeService.getAllEmployees();
    }

    //throws NotFoundException - new addition
    @DeleteMapping("/employee/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployeeById(@PathVariable Long id) throws NotFoundException {
        boolean deleted = false;
        deleted = employeeService.deleteEmployee(id);

        Map<String, Boolean> deletedMap = new HashMap();
        deletedMap.put("deleted", deleted);
        return ResponseEntity.ok(deletedMap);
    }

    @GetMapping("/employee/{id}")
    public ResponseTemplateVO getEmployeeByIdWithDepartmentId(@PathVariable Long id){
        return employeeService.getEmployeeById(id);
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@PathVariable Long id,
                                                          @RequestBody EmployeeDTO employee){
        employee = employeeService.updateEmployee(id, employee);
        return ResponseEntity.ok(employee);
    }

}

