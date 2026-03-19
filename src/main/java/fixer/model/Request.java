package fixer.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "request_app")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clientName;
    private String phone;
    private String address;
    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "master_id")
    private User assignedTo;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;


    public Request() {
    }

    public Request(Long id, String clientName, String phone, String address,
                   String description, Status status, LocalDateTime createdAt,
                   User assignedTo, User client) {
        this.id = id;
        this.clientName = clientName;
        this.phone = phone;
        this.address = address;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.assignedTo = assignedTo;
        this.client = client;
    }

    public Request(String client1, String number, String addr1, String problem1) {
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public User getAssignedTo() {
        return assignedTo;
    }

    public User getClient() {
        return client;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setAssignedTo(User assignedTo) {
        this.assignedTo = assignedTo;
    }

    public void setClient(User client) {
        this.client = client;
    }
}