package com._gechan.ticketing.types.dto;

public record SectionRequest(
        String sectionName,
        int row,
        int seatCount
) {

}
