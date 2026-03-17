package com._gechan.ticketing.secheduler;

import com._gechan.ticketing.domains.reservation.repository.ReservationRepository;
import com._gechan.ticketing.types.entity.Reservation;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ReservationExpireScheduler {

    private final ReservationRepository reservationRepository;

    public ReservationExpireScheduler(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }


    @Scheduled(fixedDelay = 60000)
    public void expireReservations() {

        List<Reservation> reservations = reservationRepository.findExpiredReservations(LocalDateTime.now());

        for (Reservation reservation : reservations) {
            reservation.expire();

        }
    }
}
