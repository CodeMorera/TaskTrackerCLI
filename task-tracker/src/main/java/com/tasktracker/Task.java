package com.tasktracker;

import java.time.Instant;
import java.util.Date;

public class Task {

    private int id;
    private String description;
    private String status;
    private final Instant createdAt;
    private Instant updatedAt;

    public Task(int id,
            String description,
            String status,
            Date createdAt,
            Date updatedAt) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.createdAt = Instant.now();
        this.updatedAt = this.createdAt;
    }

    public int getId() {
        return id;
    }

    // public void setId(int id) {
    // this.id = id;
    // }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return this.createdAt;
    }

    public void makeUpdated(){
        this.updatedAt = Instant.now();
    }

    public Instant getUpdated(){
        return this.updatedAt;
    }

    

}
