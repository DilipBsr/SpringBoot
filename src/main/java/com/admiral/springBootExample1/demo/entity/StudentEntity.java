package com.admiral.springBootExample1.demo.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="Student")
public class StudentEntity {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(name="StudentName")
    private String name;


    private String department;

    @NotNull
    private String passportNo;

    public String getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

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
}
