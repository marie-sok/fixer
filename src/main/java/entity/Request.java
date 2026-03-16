package entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Request {

    @Id
    @GeneratedValue
    private Long id;

    public Request() {
        this.id = id;
    }

    @PrePersist
    public void prePersist(){
        LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();
    }

    public Object getStatus() {
        return null;
    }

    public void setStatus() {
    }

    public void setClientName() {
    }

    public Object getId() {
        return id;
    }

}