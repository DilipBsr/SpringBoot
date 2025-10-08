package com.admiral.springBootExample1.demo.utility;

import com.admiral.springBootExample1.demo.dto.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(StudentNotFound.class)
    public ResponseEntity<ExceptionResponse> handleStudentNotFoundException(StudentNotFound studentNotFound, WebRequest webRequest){
        ExceptionResponse exceptionResponse=new ExceptionResponse();
        exceptionResponse.setMessage(studentNotFound.getMessage());
        exceptionResponse.setDetails(webRequest.getDescription(false));
        exceptionResponse.setTimeStamp(LocalDateTime.now());
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StudentAlreadyPresent.class)
    public ResponseEntity<ExceptionResponse> handleStudentAlreadyPresentException(StudentAlreadyPresent studentAlreadyPresent,WebRequest webRequest){
        ExceptionResponse exceptionResponse=new ExceptionResponse();
        exceptionResponse.setMessage(studentAlreadyPresent.getMessage());
        exceptionResponse.setDetails(webRequest.getDescription(false));
        exceptionResponse.setTimeStamp(LocalDateTime.now());
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse>handleAllException(Exception exception,WebRequest webRequest){
        ExceptionResponse exceptionResponse=new ExceptionResponse();
        exceptionResponse.setMessage(exception.getMessage());
        exceptionResponse.setDetails(webRequest.getDescription(false));
        exceptionResponse.setTimeStamp(LocalDateTime.now());
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
