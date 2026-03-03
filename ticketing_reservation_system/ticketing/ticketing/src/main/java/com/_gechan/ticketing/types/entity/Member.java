package com._gechan.ticketing.types.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private LocalDateTime createdAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    protected Member() {
    }

    public Member(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
