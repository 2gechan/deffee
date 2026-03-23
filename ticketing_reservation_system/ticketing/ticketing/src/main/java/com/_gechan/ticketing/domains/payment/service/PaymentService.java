package com._gechan.ticketing.domains.payment.service;

import com._gechan.ticketing.types.entity.Reservation;

public interface PaymentService {

    void pay(Long reservation);

}
