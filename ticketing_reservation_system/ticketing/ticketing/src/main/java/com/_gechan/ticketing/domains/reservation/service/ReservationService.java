package com._gechan.ticketing.domains.reservation.service;

import com._gechan.ticketing.types.entity.Member;

public interface ReservationService {

    void reserveSeat(Member member, Long seatId);
}
