package com.admiral.springBootExample1.demo.service;

import com.admiral.springBootExample1.demo.dto.StudentDto;
import com.admiral.springBootExample1.demo.entity.StudentEntity;
import com.admiral.springBootExample1.demo.repository.StudentRepository;
import com.admiral.springBootExample1.demo.utility.CustomExceptionHandler;
import com.admiral.springBootExample1.demo.utility.StudentAlreadyPresent;
import com.admiral.springBootExample1.demo.utility.StudentNotFound;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class StudentService {

    Logger logger= LoggerFactory.getLogger(StudentService.class);


    @Autowired
    private StudentRepository studentRepository;

    ModelMapper mapper= new ModelMapper();


    public List<StudentDto> getAllStudents(){
        List<StudentDto> sDto=new ArrayList<>();
        List<StudentEntity> studentEntities = studentRepository.findAll();
        for (StudentEntity s:studentEntities) {
            StudentDto studentDto=mapper.map(s,StudentDto.class);
            sDto.add(studentDto);
        }
        return sDto;
    }

    @Cacheable(cacheNames = {"studentCache"},key = "#id")
    @Async
    public CompletableFuture<StudentDto> getStudent(Long id) throws StudentNotFound, Exception{
        simulateBackendCall();
        Optional<StudentEntity> studentEntity=studentRepository.findById(id);
        if(studentEntity.isEmpty()){
            throw new StudentNotFound("Student Not Found!!");
        }
        StudentDto studentDto=mapper.map(studentEntity,StudentDto.class);
        logger.info("Student : "+studentDto.getId());
        return CompletableFuture.completedFuture(studentDto);
    }

    public void simulateBackendCall() {
        try {
            System.out.println("------------- Going to sleep for 5 seconds to simulate Backend Delay -----------");
            Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Adding Student
    public Long addStudent(StudentDto studentDto) throws StudentAlreadyPresent ,Exception{
//        StudentEntity studentEntity=mapper.map(studentDto,StudentEntity.class );
        StudentEntity studentEntity=new StudentEntity();
        studentEntity.setName(studentDto.getName());
        studentEntity.setDepartment(studentDto.getDepartment());
        studentEntity.setPassportNo(studentDto.getPassportNo());

        Optional<StudentEntity> studentOptional=studentRepository.findByPassportNo(studentEntity.getPassportNo());

        if(!studentOptional.isEmpty()){
            throw new StudentAlreadyPresent("Student Already Present!!");
        }
        studentEntity=studentRepository.save(studentEntity);
        logger.info("Student : "+studentDto.getId()+" Added!!");
        return studentEntity.getId();
    }

    @CachePut(cacheNames = "studentCache",key = "#id")
    public String updateStudent(StudentDto studentDto,Long id) throws StudentNotFound,Exception{
//        StudentEntity studentEntity=mapper.map(studentDto,StudentEntity.class );
        Optional<StudentEntity> studentOptional=studentRepository.findById(id);

        if(studentOptional.isEmpty()){
//            throw new Exception("Student Not Found!!");
            throw new StudentNotFound("Student Not Found!!");
        }
        StudentEntity studentEntity=mapper.map(studentDto,StudentEntity.class);
        studentEntity.setId(studentOptional.get().getId());
        studentRepository.save(studentEntity);
        logger.info("Student : "+studentDto.getId()+" Updated!!");
        return "Student Updated Successfully!! "+id;
    }



}
