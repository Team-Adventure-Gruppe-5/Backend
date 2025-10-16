package com.example.adventurexpbackend.controller;

import com.example.adventurexpbackend.model.Employee;
import com.example.adventurexpbackend.model.Role;
import com.example.adventurexpbackend.repository.EmployeeRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
@ActiveProfiles("test")
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // Konvertere Java-objektet til JSON
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EmployeeRepo employeeRepo;

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = new Employee(1, "Bluey", "Bandit", "blba", "1234", Role.ADMIN);
        employee = new Employee(2, "Bingo", "Chilli", "bich", "1234", Role.ACTIVITY_EMPLOYEE);
    }

    @Test
    void employeepage() throws Exception {
        // ACT & ASSERT
        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void createEmployee() throws Exception {
        // ARRANGE
        Employee newEmp = new Employee("Muffin", "Heeler", "muhe", "1234", Role.ADMIN);
        when(employeeRepo.save(any(Employee.class))).thenReturn(newEmp);

        String empAsJSON = objectMapper.writeValueAsString(newEmp);

        // ACT & ASSERT
        mockMvc.perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(empAsJSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstname").value("Muffin"))
                .andExpect(jsonPath("$.role").value("ADMIN"));
    }

    @Test
    void deleteEmp() throws Exception {
        // ARRANGE
        when(employeeRepo.findById(1)).thenReturn(Optional.of(employee));

        // ACT & ASSERT
        mockMvc.perform(delete("/employees/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Employee deleted"));

        verify(employeeRepo).findById(1);
        verify(employeeRepo, times(1)).deleteById(1);
    }

    @Test
    void deleteEmp_NonExisting() throws Exception {
        // ARRANGE
        when(employeeRepo.findById(3)).thenReturn(Optional.empty());

        // ACT & ASSERT
        mockMvc.perform(delete("/employees/3").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Employee not found"));
    }
}