package com._gechan.ticketing.types.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.cglib.core.Local;

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

    public void reserve() {

        LocalDateTime now = LocalDateTime.now();
        if (this.status != ReservationStatus.PENDING) {
            throw new IllegalStateException("Reservation cannot be reserved");
        }

        if (this.reservedAt != null) {
            throw new IllegalStateException("이미 예약 처리된 요청");
        }

        this.seat.reserve();
        this.reservedAt = now;
        this.expiredAt = now.plusMinutes(5);

    }

    public void cancel() {

        if (this.status != ReservationStatus.PENDING &&
                this.status != ReservationStatus.COMPLETED) {
            throw new IllegalStateException("취소 불가 상태");
        }

        this.seat.release();
        this.status = ReservationStatus.CANCELED;
    }

    public void expire() {
        if (this.status != ReservationStatus.PENDING) {
            return;
        }

        this.seat.release();
        this.status = ReservationStatus.EXPIRED;

    }

    public void complete() {

        if (this.status != ReservationStatus.PENDING) {
            throw new IllegalStateException("확정 불가 상태");
        }

        if (LocalDateTime.now().isAfter(this.expiredAt)) {
            throw new IllegalStateException("이미 만료된 예약");
        }

        if (this.seat.getStatus() != SeatStatus.RESERVED) {
            throw new IllegalStateException("좌석 상태 불일치");
        }

        this.status = ReservationStatus.COMPLETED;
    }

    // 결제 시점에 예약 만료 스케쥴러에 의해 만료되지 않도록 PROCESSING 상태로 변경
    public void startPayment() {

        if (this.status != ReservationStatus.PENDING) {
            throw new IllegalStateException("결제 할 수 없는 예약 입니다.");
        }

        this.status = ReservationStatus.PROCESSING;
    }
}
