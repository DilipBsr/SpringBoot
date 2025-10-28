package com.admiral.springBootExample1.demo.service;

import com.admiral.springBootExample1.demo.dto.StudentDto;
import com.admiral.springBootExample1.demo.entity.StudentEntity;
import com.admiral.springBootExample1.demo.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class StudentServiceTest {
    StudentDto studentDto=new StudentDto();
    StudentEntity studentEntity=new StudentEntity();
    @InjectMocks
    StudentService studentService;
    @Mock
    StudentRepository studentRepository;
    @BeforeEach
    public void initialiseMockValue(){
        studentDto.setName("Bhola");
        studentDto.setPassportNo("djdk");
        studentDto.setDepartment("IT");

        studentEntity.setName("Bhola");
        studentEntity.setPassportNo("djdk");
        studentEntity.setDepartment("IT");
        studentEntity.setId(123443L);
    }

    @Test
    public void addStudentHappyPath()throws Exception{
        Optional<StudentEntity> studentOptional=Optional.ofNullable(null);
        Mockito.when(studentRepository.findByPassportNo(Mockito.any())).thenReturn(studentOptional);
        Mockito.when(studentRepository.save(Mockito.any())).thenReturn(studentEntity);
        assertEquals(123443L,studentService.addStudent(studentDto));
//        assertThrows()
    }
}
