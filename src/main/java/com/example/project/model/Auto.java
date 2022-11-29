package com.example.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class Auto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Client client;

    private String brand;

    private String year;

    private String type;

    public Auto(Client client, String brand, String year, String type) {
        this.client = client;
        this.brand = brand;
        this.year = year;
        this.type = type;
    }

    public Auto() {

    }
}
