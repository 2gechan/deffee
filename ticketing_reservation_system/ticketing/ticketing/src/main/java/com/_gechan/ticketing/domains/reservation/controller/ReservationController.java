package com._gechan.ticketing.domains.reservation.controller;

import com._gechan.ticketing.domains.reservation.service.ReservationService;
import com._gechan.ticketing.types.dto.MemberRequest;
import com._gechan.ticketing.types.dto.ReservationCancelRequest;
import com._gechan.ticketing.types.dto.ReservationRequest;
import com._gechan.ticketing.types.entity.Member;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    public void reserveSeat(
            @RequestBody ReservationRequest reservationRequest,
            @RequestAttribute MemberRequest memberRequest
            ) {
        reservationService.reserveSeat(memberRequest.memberId(), reservationRequest.seatId());
    }

    public void cancelSeat(
            @RequestBody ReservationCancelRequest reservationCancelRequest,
            @RequestAttribute MemberRequest memberRequest
    ) {
        reservationService.cancelSeat(memberRequest.memberId(), reservationCancelRequest.reservationId());
    }
}
