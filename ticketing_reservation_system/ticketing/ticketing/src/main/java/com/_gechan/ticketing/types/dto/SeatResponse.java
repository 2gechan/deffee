package com._gechan.ticketing.types.dto;

import com._gechan.ticketing.types.entity.SeatStatus;

public record SeatResponse(
        Long id,
        String section,
        int rowNumber,
        int seatNumber,
        SeatStatus status
) {
}
