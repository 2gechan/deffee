package com._gechan.ticketing.domains.seat.service;

import com._gechan.ticketing.types.dto.SeatResponse;
import com._gechan.ticketing.types.entity.Seat;

import java.util.List;

public interface SeatService {

    List<SeatResponse> getSeats(Long concertId);
}
