package com.admiral.springBootExample1.demo.dto;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.NotNull;

public class StudentDto {
    @NotNull
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(name="StudentName")
    private String name;


    private String department;

    @NotNull
    private String passportNo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }
}
