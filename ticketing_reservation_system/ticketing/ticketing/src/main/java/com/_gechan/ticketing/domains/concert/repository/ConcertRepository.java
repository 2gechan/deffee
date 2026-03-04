package com._gechan.ticketing.domains.concert.repository;

import com._gechan.ticketing.types.entity.Concert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcertRepository extends JpaRepository<Concert, Long> {
}
