package com._gechan.ticketing.types.dto;

import java.time.LocalDateTime;
import java.util.List;

public record CreateConcertRequest (
        String title,
        LocalDateTime concertDate,
        List<SectionRequest> sections
){

}
