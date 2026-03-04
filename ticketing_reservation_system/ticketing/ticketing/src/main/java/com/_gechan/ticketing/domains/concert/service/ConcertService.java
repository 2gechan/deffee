package com._gechan.ticketing.domains.concert.service;

import com._gechan.ticketing.types.dto.CreateConcertRequest;

public interface ConcertService {

    public void createConcert(CreateConcertRequest request);
}
