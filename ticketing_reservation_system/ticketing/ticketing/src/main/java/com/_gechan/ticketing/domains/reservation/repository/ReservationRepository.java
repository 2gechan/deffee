package com._gechan.ticketing.domains.reservation.repository;

import com._gechan.ticketing.types.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("""
    SELECT r FROM Reservation r
    WHERE r.status = 'PENDING'
    AND r.expiredAt <= :now
    """)
    List<Reservation> findExpiredReservations(LocalDateTime now);
}
