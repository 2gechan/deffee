package com._gechan.ticketing.domains.concert.service;

import com._gechan.ticketing.domains.concert.repository.ConcertRepository;
import com._gechan.ticketing.domains.seat.repository.SeatRepository;
import com._gechan.ticketing.types.dto.CreateConcertRequest;
import com._gechan.ticketing.types.dto.SectionRequest;
import com._gechan.ticketing.types.entity.Concert;
import com._gechan.ticketing.types.entity.Seat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConcertServiceImpl implements ConcertService {

    private final ConcertRepository concertRepository;
    private final SeatRepository seatRepository;

    public ConcertServiceImpl(ConcertRepository concertRepository, SeatRepository seatRepository) {
        this.concertRepository = concertRepository;
        this.seatRepository = seatRepository;
    }

    @Override
    @Transactional
    public void createConcert(CreateConcertRequest request) {

        Concert concert = concertRepository.save(new Concert(request.title(), request.concertDate()));

        List<Seat> seatList = new ArrayList<>();

        for (SectionRequest section : request.sections()) {
            for (int row = 1; row <= section.row(); row++) {
                for (int seatNumber = 1; seatNumber <= section.seatCount(); seatNumber++) {
                    Seat seat = new Seat(concert, section.sectionName(), row, seatNumber);
                    seatList.add(seat);
                }
            }
        }
        seatRepository.saveAll(seatList);
    }
}
