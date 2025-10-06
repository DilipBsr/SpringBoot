package com.admiral.springBootExample1.demo.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {

    @GetMapping("/example")
    public String exampleGetAPI(){
        return "This is Get API...";
    }
}
