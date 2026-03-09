package com._gechan.ticketing.domains.seat.controller;

import com._gechan.ticketing.domains.seat.service.SeatService;
import com._gechan.ticketing.types.dto.SeatResponse;
import com._gechan.ticketing.types.entity.Seat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/seat")
public class SeatController {

    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping("/{concertId}/seats")
    public List<SeatResponse> getSeats(@PathVariable Long concertId) {

        List<SeatResponse> seats = seatService.getSeats(concertId);

        return seats;
    }
}
