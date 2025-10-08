package com.admiral.springBootExample1.demo.controller;


import com.admiral.springBootExample1.demo.dto.StudentDto;
import com.admiral.springBootExample1.demo.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping("/example")
    public String exampleGetAPI(){
        return "This is Get API...";
    }

    @GetMapping()
    public List<StudentDto> getAllStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public StudentDto getStudent(@PathVariable Long id) throws Exception {
        return studentService.getStudent(id);
    }

    @PostMapping()
    public ResponseEntity<Long>addStudent(@Valid @RequestBody StudentDto studentDto)throws Exception{
        Long id=studentService.addStudent(studentDto);
        ResponseEntity<Long> student=new ResponseEntity<Long>(id, HttpStatus.CREATED);
        return student;
    }

    @PutMapping("/{id}")
    public ResponseEntity<String>updateStudent(@Valid @RequestBody StudentDto studentDto,@PathVariable Long id)throws Exception{
        String response=studentService.updateStudent(studentDto,id);
        return new ResponseEntity<String>(response,HttpStatus.ACCEPTED);
    }

}
