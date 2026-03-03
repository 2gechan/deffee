package com._gechan.ticketing.types.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Concert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private LocalDateTime concertDate;

    private Long totalSeats;

    private LocalDateTime createdAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    protected Concert() {
    }

    public Concert(String title, LocalDateTime concertDate, Long totalSeats) {
        this.title = title;
        this.concertDate = concertDate;
        this.totalSeats = totalSeats;
    }
}
