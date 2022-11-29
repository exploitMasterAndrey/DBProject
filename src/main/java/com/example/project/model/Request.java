package com.example.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeId;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Data
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String date;
    private Integer price;
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Client client;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "request") //ON DELETE SET NULL
    @JsonIgnore
    private List<Worker> workers;

    public Request(String date, Integer price, String comment, Client client) {
        this.date = date;
        this.price = price;
        this.comment = comment;
        this.client = client;
    }

    public Request() {

    }
}
