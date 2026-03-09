package com._gechan.ticketing.domains.seat.repository;

import com._gechan.ticketing.types.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

    List<Seat> findByConcertIdOrderBySectionAscRowNumberAscSeatNumberAsc(Long concertId);
}
