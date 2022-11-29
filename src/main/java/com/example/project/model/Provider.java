package com.example.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String deliveryPartType;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) //ON DELETE SET NULL
    private Branch branch;

    public Provider(String deliveryPartType, String name, Branch branch) {
        this.deliveryPartType = deliveryPartType;
        this.name = name;
        this.branch = branch;
    }

    public Provider() {

    }


}
