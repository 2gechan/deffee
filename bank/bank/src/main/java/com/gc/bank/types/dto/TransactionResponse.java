package com.gc.bank.types.dto;

import com.gc.bank.types.entity.AccountTransactionLog;

import java.math.BigDecimal;
import java.time.Instant;

public record TransactionResponse (
        Long accountId,
        String type,
        BigDecimal amount,
        Instant occurredAt
)
{
    public static TransactionResponse from(AccountTransactionLog log) {
        return new TransactionResponse(
                log.getAccountId(),
                log.getType().name(),
                log.getAmount(),
                log.getOccurredAt()
        );
    }
}
