package com.example.task3.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Task {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String description;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users owner;
}
