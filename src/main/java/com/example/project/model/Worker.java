package com.example.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String surname;
    private String name;
    private String patronymic;
    private String birthDate;
    private String phone;
    private Integer experience;
    private String position;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Request request;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Branch branch;

    public Worker(String surname, String name, String patronymic, String birthDate, String phone, Integer experience, String position, Branch branch) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.birthDate = birthDate;
        this.phone = phone;
        this.experience = experience;
        this.position = position;
        this.branch = branch;
    }

    public Worker() {

    }
}
