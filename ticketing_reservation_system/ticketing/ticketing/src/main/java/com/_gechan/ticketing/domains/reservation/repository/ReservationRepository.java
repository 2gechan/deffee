package com._gechan.ticketing.domains.reservation.repository;

import com._gechan.ticketing.types.entity.Reservation;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("""
    SELECT r FROM Reservation r
    WHERE r.status = 'PENDING'
    AND r.expiredAt <= :now
    """)
    List<Reservation> findExpiredReservations(LocalDateTime now);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select r from Reservation r where r.id = :id")
    Optional<Reservation> findByIdWithLock(Long reservationId);
}
