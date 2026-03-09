package com._gechan.ticketing.domains.concert.controller;

import com._gechan.ticketing.domains.concert.service.ConcertService;
import com._gechan.ticketing.types.dto.CreateConcertRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/concert")
public class ConcertController {

    private final ConcertService concertService;

    public ConcertController(ConcertService concertService) {
        this.concertService = concertService;
    }

    public void createConcert(CreateConcertRequest request) {
        concertService.createConcert(request);
    }
}
