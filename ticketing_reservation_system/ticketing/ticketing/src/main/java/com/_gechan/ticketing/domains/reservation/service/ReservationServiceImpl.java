package com._gechan.ticketing.domains.reservation.service;

import com._gechan.ticketing.domains.member.repository.MemberRepository;
import com._gechan.ticketing.domains.payment.service.PaymentService;
import com._gechan.ticketing.domains.reservation.repository.ReservationRepository;
import com._gechan.ticketing.domains.seat.repository.SeatRepository;
import com._gechan.ticketing.types.entity.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final SeatRepository seatRepository;
    private final MemberRepository memberRepository;
    private final PaymentService paymentService;

    public ReservationServiceImpl(ReservationRepository reservationRepository, SeatRepository seatRepository, MemberRepository memberRepository, PaymentService paymentService) {
        this.reservationRepository = reservationRepository;
        this.seatRepository = seatRepository;
        this.memberRepository = memberRepository;
        this.paymentService = paymentService;
    }

    @Override
    public void reserveSeat(Long memberId, Long seatId) {

        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException("Member not found")
        );

        Seat seat = seatRepository.findByIdWithLock(seatId).orElseThrow(
                () -> new IllegalArgumentException("Seat not found")
        );

        LocalDateTime now = LocalDateTime.now();
        Reservation reservation = reservationRepository.save(
                new Reservation(
                        member,
                        seat,
                        ReservationStatus.PENDING,
                        now,
                        now.plusMinutes(5)
                )
        );

        reservation.reserve();

    }

    @Override
    public void cancelSeat(Long memberId, Long reservationId) {

        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(
                () -> new IllegalArgumentException("Reservation not found")
        );

        if (!reservation.getMember().getId().equals(memberId)) {
            throw new IllegalStateException("Not your reservation");
        }

        reservation.cancel();

    }

    @Override
    public void complete(Long reservationId) {

//        Reservation reservation = reservationRepository.findByIdWithLock(reservationId)
//                .orElseThrow(() -> new IllegalArgumentException("예약이 만료되었거나 존재하지 않습니다."));
//
//        if (reservation.getStatus() != ReservationStatus.PENDING) {
//            throw new IllegalStateException("이미 처리된 예약 입니다.");
//        }
//        paymentService.pay(reservation);
        startPayment(reservationId);

        try {
            paymentService.pay(reservationId);

            completeReservation(reservationId);
        } catch (Exception e) {

        }

    }

    @Transactional
    @Override
    public void startPayment(Long reservationId) {

        Reservation reservation = reservationRepository.findByIdWithLock(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("예약 없음"));

        if (reservation.getStatus() != ReservationStatus.PENDING) {
            throw new IllegalStateException("이미 처리된 예약");
        }

        reservation.startPayment();

    }

    @Transactional
    @Override
    public void completeReservation(Long reservationId) {

        Reservation reservation = reservationRepository.findByIdWithLock(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("예약 없음"));

        reservation.complete();
    }
}
