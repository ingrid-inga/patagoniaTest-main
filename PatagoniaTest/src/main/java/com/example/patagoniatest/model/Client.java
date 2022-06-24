package com.example.patagoniatest.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "client")
public class Client {
    @Id
    private Long id;
    private String fullName;
    private Integer income;

    public Client(Long id, String fullName, Integer income) {
        this.id = id;
        this.fullName = fullName;
        this.income = income;
    }

    public Client() {
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public Integer getIncome() {
        return income;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setIncome(Integer income) {
        this.income = income;
    }
}
