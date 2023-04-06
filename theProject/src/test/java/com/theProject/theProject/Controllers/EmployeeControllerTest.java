package com.theProject.theProject.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.theProject.theProject.Model.EmployeeDTO;
import com.theProject.theProject.Services.EmployeeService;
import com.theProject.theProject.ValueObjects.Department;
import com.theProject.theProject.ValueObjects.ResponseTemplateVO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {

    @Autowired
    MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();


    @Mock
    private EmployeeService employeeService;
    @InjectMocks
    private EmployeeController employeeController;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);//initializes the Mockito
        this.mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();//initializes the MockMvc
    }

    EmployeeDTO Record_1 = new EmployeeDTO(37L, "Danile", "Huji",
            "danHuj@gmail.com", "ehehetd4456",
            "Smith St", "0987", "234",
            "Yes", "sales", 12L);
    EmployeeDTO Record_2 = new EmployeeDTO(39L, "Dan", "Hujo",
            "danHujo@gmail.com", "q22egf5",
            "Smith St", "0986", "234",
            "Yes", "sales", 12L);
    EmployeeDTO Record_3 = new EmployeeDTO(36L, "Danny", "Huji",
            "danny@gmail.com", "hhsgs674tt",
            "Smith St", "0987", "234",
            "Yes", "sales", 12L);

    @Test //ok
    public void getAllEmployeesTest() throws Exception{

        List<EmployeeDTO> employees = new ArrayList<>(Arrays.asList(Record_1, Record_2, Record_3));

        Mockito.when(employeeService.getAllEmployees()).thenReturn(employees);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/employee")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].employeeFirstName", is("Danile")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].employeeLastName", is("Huji")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].employeeId", is(39)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].employeePhoneNumber", is("0986")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].salaryPA", is("234")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].b2b", is("Yes")));

    }

    @Test //ok
    public void getEmployeeByIdTest() throws Exception{

        Department department = new Department(111L, "Marketing", "Carl Jung");

        ResponseTemplateVO responseTemplateVO = new ResponseTemplateVO(Record_1, department);

        Mockito.when(employeeService.getEmployeeById(Record_1.getEmployeeId())).thenReturn(responseTemplateVO);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/employee/37")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.employee.employeeFirstName", is("Danile")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.employee.employeeLastName", is("Huji")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.department.departmentId", is(111)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.department.departmentName", is("Marketing")));

    }

    @Test//ok
    public void createEmployeesTest() throws Exception{

        EmployeeDTO employee = EmployeeDTO.builder()
                .employeeId(222L).employeeFirstName("Ronan")
                .employeeLastName("Behan").employeeEmail("ronanbehan@gmail.com")
                .employeePassword("12345").employeeAddress("St St")
                .employeePhoneNumber("0985").salaryPA("0987")
                .team("ABC").b2b("Yes").departmentId(42L).build();

        Mockito.when(employeeService.createEmployee(employee)).thenReturn(employee);

        String content = objectWriter.writeValueAsString(employee);//converts POJO to String

        MockHttpServletRequestBuilder mockRequest =  MockMvcRequestBuilders //create the request
                        .post("/api/v1/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.employeeFirstName", is("Ronan")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.employeeLastName", is("Behan")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.employeePhoneNumber", is("0985")));

    }

    @Test
    public void updateEmployeesTest() throws Exception{
        //remember you are only getting the employee back at the end of this, not the department

        Mockito.when(employeeService.updateEmployee(Record_1.getEmployeeId(), Record_2)).thenReturn(Record_2);

        String content = objectWriter.writeValueAsString(Record_2);//converts POJO to String

        MockHttpServletRequestBuilder mockRequest =  MockMvcRequestBuilders //create the request
                .put("/api/v1/employee/37")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.employeeFirstName", is("Dan")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.employeeLastName", is("Hujo")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.employeePhoneNumber", is("0986")));
    }

    @Test
    public void deleteEmployeesTest() throws Exception{
        Mockito.when(employeeService.deleteEmployee(Record_1.getEmployeeId())).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/v1/employee/37")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
    }

}