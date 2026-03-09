package com._gechan.ticketing.domains.reservation.service;

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

    public ReservationServiceImpl(ReservationRepository reservationRepository, SeatRepository seatRepository) {
        this.reservationRepository = reservationRepository;
        this.seatRepository = seatRepository;
    }

    @Override
    public void reserveSeat(Member member, Long seatId) {

        Seat seat = seatRepository.findById(seatId).orElseThrow(
                () -> new RuntimeException("Seat not found")
        );

        seat.reserve();

        Reservation reservation = reservationRepository.save(
                new Reservation(
                        member,
                        seat,
                        ReservationStatus.PENDING,
                        LocalDateTime.now(),
                        LocalDateTime.now().plusMinutes(5)
                )
        );

    }
}
