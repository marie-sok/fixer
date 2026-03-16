package fixer.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_table")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User() {}

    public User(String name, Role role) {
        this.name = name;
        this.role = role;
    }

    public User(String name, String role) {
        this.name = name;
        this.role = Role.valueOf(role.toUpperCase());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Role getRole() {
        return role;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}