package com.example.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Data
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String specialization;
    private String address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "branch")
    @JsonIgnore
    private List<Provider> providers;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "branch")
    @JsonIgnore
    private List<Worker> workers;

    public Branch(String specialization, String address) {
        this.specialization = specialization;
        this.address = address;
    }

    public Branch() {

    }
}
