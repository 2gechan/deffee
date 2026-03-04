package com._gechan.ticketing.types.entity;

import jakarta.persistence.*;

@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"concert_id", "section", "row_number", "seat_number"})
        }
)
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "concert_id", nullable = false)
    private Concert concert;

    private String section;

    @Column(name = "row_number")
    private int rowNumber;

    @Column(name = "seat_number")
    private int seatNumber;

    @Enumerated(EnumType.STRING)
    private SeatStatus status;

    @Version
    private Long version;

    protected Seat() {
    }

    public Seat(Concert concert, String section, int rowNumber, int seatNumber) {
        this.concert = concert;
        this.section = section;
        this.rowNumber = rowNumber;
        this.seatNumber = seatNumber;
        this.status = SeatStatus.AVAILABLE;
    }
}
