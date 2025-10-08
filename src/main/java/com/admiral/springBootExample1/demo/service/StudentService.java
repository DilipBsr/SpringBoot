package com.admiral.springBootExample1.demo.service;

import com.admiral.springBootExample1.demo.dto.StudentDto;
import com.admiral.springBootExample1.demo.entity.StudentEntity;
import com.admiral.springBootExample1.demo.repository.StudentRepository;
import com.admiral.springBootExample1.demo.utility.StudentAlreadyPresent;
import com.admiral.springBootExample1.demo.utility.StudentNotFound;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {


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

    public StudentDto getStudent(Long id) throws StudentNotFound, Exception{
        Optional<StudentEntity> studentEntity=studentRepository.findById(id);
        if(studentEntity.isEmpty()){
            throw new StudentNotFound("Student Not Found!!");
        }
        StudentDto studentDto=mapper.map(studentEntity,StudentDto.class);
        return studentDto;
    }

    public Long addStudent(StudentDto studentDto) throws StudentAlreadyPresent ,Exception{
        StudentEntity studentEntity=mapper.map(studentDto,StudentEntity.class );
        Optional<StudentEntity> studentOptional=studentRepository.findByPassportNo(studentEntity.getPassportNo());
        if(!studentOptional.isEmpty()){
            throw new StudentAlreadyPresent("Student Already Present!!");
        }
        studentEntity=studentRepository.save(studentEntity);
        return studentEntity.getId();
    }

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
        return "Student Updated Successfully!! "+id;
    }
}
