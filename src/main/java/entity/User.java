package com.example.repair.model;

import jakarta.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String role;

    public Long getId() { return id; }

    public String getName() { return name; }

    public String getRole() { return role; }

    public void setName(String name) { this.name = name; }

    public void setRole(String role) { this.role = role; }
}