package com.gc.bank.domains.transaction.event;

import java.time.Instant;

public interface DomainEvent {

    EventType eventType();
    Instant occurredAt();
}
