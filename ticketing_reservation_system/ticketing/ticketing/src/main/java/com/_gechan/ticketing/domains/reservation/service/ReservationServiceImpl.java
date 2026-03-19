package com._gechan.ticketing.domains.reservation.service;

import com._gechan.ticketing.domains.member.repository.MemberRepository;
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

    public ReservationServiceImpl(ReservationRepository reservationRepository, SeatRepository seatRepository, MemberRepository memberRepository) {
        this.reservationRepository = reservationRepository;
        this.seatRepository = seatRepository;
        this.memberRepository = memberRepository;
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
}
