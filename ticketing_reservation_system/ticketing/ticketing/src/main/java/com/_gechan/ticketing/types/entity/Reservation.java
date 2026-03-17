package com._gechan.ticketing.types.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "member_id")
    @ManyToOne
    private Member member;

    @JoinColumn(name = "seat_id")
    @ManyToOne // 취소할 경우 같은 seat가 재예약 될 수 있음
    private Seat seat;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    private LocalDateTime reservedAt;

    private LocalDateTime expiredAt;

    protected Reservation() {
    }

    public Reservation(Member member, Seat seat, ReservationStatus status, LocalDateTime reservedAt, LocalDateTime expiredAt) {
        this.member = member;
        this.seat = seat;
        this.status = status;
        this.reservedAt = reservedAt;
        this.expiredAt = expiredAt;
    }

    public void cancel() {

        if (this.status != ReservationStatus.PENDING) {
            throw new IllegalStateException("Reservation cannot be cancel");
        }

        this.status = ReservationStatus.CANCELED;

        this.seat.release();
    }

    public void expire() {
        if (this.status != ReservationStatus.PENDING) {
            return;
        }

        this.status = ReservationStatus.EXPIRED;
        this.seat.release();
    }
}
