package com.example.project.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String surname;
    private String name;
    private String patronymic;
    private String birthDate;
    private String phone;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Auto> autos;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    @JsonIgnore
    private List<Request> requests;

    public Client(String surname, String name, String patronymic, String birthDate, String phone) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.birthDate = birthDate;
        this.phone = phone;
    }

    public Client() {

    }
}
