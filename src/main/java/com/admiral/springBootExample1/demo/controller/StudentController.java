package com.admiral.springBootExample1.demo.controller;


import com.admiral.springBootExample1.demo.dto.StudentDto;
import com.admiral.springBootExample1.demo.service.StudentService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    Logger logger= LoggerFactory.getLogger(StudentController.class);

    @Autowired
    StudentService studentService;

    @GetMapping("/example")
    public String exampleGetAPI(){
        logger.info("Get all Student..");
        return "This is Get API...";
    }

    @GetMapping()
    public List<StudentDto> getAllStudents(){
        logger.info("Get all Student..");
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public StudentDto getStudent(@PathVariable Long id) throws Exception {
        logger.info("Get one Student..");
        return studentService.getStudent(id);
    }

    @PostMapping()
    public ResponseEntity<Long>addStudent(@Valid @RequestBody StudentDto studentDto)throws Exception{
        logger.info("Adding Student...");
        Long id=studentService.addStudent(studentDto);
        ResponseEntity<Long> student=new ResponseEntity<Long>(id, HttpStatus.CREATED);
        logger.info("Student Added!");
        return student;
    }

    @PutMapping("/{id}")
    public ResponseEntity<String>updateStudent(@Valid @RequestBody StudentDto studentDto,@PathVariable Long id)throws Exception{

        String response=studentService.updateStudent(studentDto,id);
        logger.info("Update Student.");
        return new ResponseEntity<String>(response,HttpStatus.ACCEPTED);
    }

}
