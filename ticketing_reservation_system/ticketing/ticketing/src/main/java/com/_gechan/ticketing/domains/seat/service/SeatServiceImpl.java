package com._gechan.ticketing.domains.seat.service;

import com._gechan.ticketing.domains.seat.repository.SeatRepository;
import com._gechan.ticketing.types.dto.SeatResponse;
import com._gechan.ticketing.types.entity.Seat;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;

    public SeatServiceImpl(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    @Override
    public List<SeatResponse> getSeats(Long concertId) {

        List<Seat> seats = seatRepository.findByConcertIdOrderBySectionAscRowNumberAscSeatNumberAsc(concertId);

        return seats.stream().map(
                seat -> new SeatResponse(
                        seat.getId(),
                        seat.getSection(),
                        seat.getRowNumber(),
                        seat.getSeatNumber(),
                        seat.getStatus()
                )
        ).toList();

    }
}
