package com._gechan.ticketing.domains.concert.controller;

import com._gechan.ticketing.types.dto.CreateConcertRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/concert")
public class ConcertController {

    public void createConcert(CreateConcertRequest request) {

    }
}
