package com.example.backend;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "expenses")
@Getter
@Setter
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Double amount;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // Expense.java
    @PrePersist
    public void onCreate() {
    if (createdAt == null) {
        createdAt = java.time.LocalDateTime.now();
    }
}
}
