package fixer.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "request")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clientName;
    private String phone;
    private String address;
    private String description;

    @Enumerated(EnumType.STRING)
    private Status status = Status.NEW;

    @ManyToOne
    @JoinColumn(name = "assigned_to")
    private User assignedTo;

    private LocalDateTime createdAt = LocalDateTime.now();

    public Request() {}

    public Request(String clientName, String phone, String address, String description) {
        this.clientName = clientName;
        this.phone = phone;
        this.address = address;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getClientName() {
        return clientName;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    public User getAssignedTo() {
        return assignedTo;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setAssignedTo(User assignedTo) {
        this.assignedTo = assignedTo;
    }
}