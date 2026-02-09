package com.gc.bank.kafka;

import java.time.Instant;

public interface DomainEvent {

    EventType eventType();
    Instant occurredAt();
}
