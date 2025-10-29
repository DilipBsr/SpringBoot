package com.admiral.springBootExample1.demo.controller;

import com.admiral.springBootExample1.demo.dto.StudentDto;
import com.admiral.springBootExample1.demo.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTest {
    @MockitoBean
    StudentService mockStudentService;

    @Autowired
    private MockMvc mockMvc;

    StudentDto mockStudentDto=new StudentDto();
    StudentDto mockStudentDto2=new StudentDto();

    List<StudentDto> mockStudentDtoList=new ArrayList<>();
    @BeforeEach
    public void setup(){
        mockStudentDto.setName("Bhola");
        mockStudentDto.setPassportNo("djdk");
        mockStudentDto.setDepartment("IT");
        mockStudentDto.setId(123L);

        mockStudentDto2.setName("Dilip");
        mockStudentDto2.setPassportNo("abc");
        mockStudentDto2.setDepartment("IT");
        mockStudentDto2.setId(1234L);

        mockStudentDtoList.add(mockStudentDto);
        mockStudentDtoList.add(mockStudentDto2);

    }

    @Test
    public void getAllStudentTestSuccess() throws Exception{
        Mockito.when(mockStudentService.getAllStudents()).thenReturn(mockStudentDtoList);

        mockMvc.perform(get("/student"))
                .andExpect(status().is2xxSuccessful())
        ;
    }

    @Disabled("Under Development!!")
    @Test
    public void addStudentTestSuccess() throws Exception{

        ObjectMapper mapper = new ObjectMapper();//convert object into json
        String json = mapper.writeValueAsString(mockStudentDto);

        System.out.println(json);
        Mockito.when(mockStudentService.addStudent(Mockito.any())).thenReturn(123L);

        MvcResult mvcResult= mockMvc.perform(post("/student")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated()).andReturn();

        assertEquals("123",mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void addStudentTestValidationBody() throws Exception{

        ObjectMapper mapper = new ObjectMapper();//convert object into json
        String json = mapper.writeValueAsString("{\"id\":123,\"name\":\"Bhola\",\"department\":\"IT\"}");

        System.out.println(json);
        Mockito.when(mockStudentService.addStudent(Mockito.any())).thenReturn(123L);

        MvcResult mvcResult= mockMvc.perform(post("/student")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest()).andReturn();

    }
}
