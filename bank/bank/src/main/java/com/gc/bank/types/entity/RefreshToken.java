package com.gc.bank.types.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class RefreshToken {


    @Id
    @GeneratedValue
    private Long id;

    private String userId;

    @Column(length = 500)
    private String token;

    private LocalDateTime expiredAt;

}
