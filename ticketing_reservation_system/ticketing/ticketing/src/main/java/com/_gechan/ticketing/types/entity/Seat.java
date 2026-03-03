package com._gechan.ticketing.types.entity;

import jakarta.persistence.*;

@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"concert_id", "seatNumber"})
        }
)
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "concert_id")
    private Concert concert;

    private Long seatNumber;

    @Enumerated(EnumType.STRING)
    private SeatStatus status;

    @Version
    private Long version;

    protected Seat() {
    }

    public Seat(Concert concert, Long seatNumber, SeatStatus status, Long version) {
        this.concert = concert;
        this.seatNumber = seatNumber;
        this.status = status;
        this.version = version;
    }
}
