package com._gechan.ticketing.domains.seat.repository;

import com._gechan.ticketing.types.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {
}
