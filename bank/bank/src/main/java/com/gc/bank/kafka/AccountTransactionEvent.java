package com.gc.bank.kafka;

import java.math.BigDecimal;
import java.time.Instant;

public record AccountTransactionEvent(
        Long memberId,
        Long accountId,
        BigDecimal amount,
        TransactionType type,
        Instant occurredAt
) implements DomainEvent {

    @Override
    public EventType eventType() {
        return EventType.ACCOUNT_TRANSACTION;
    }

}
