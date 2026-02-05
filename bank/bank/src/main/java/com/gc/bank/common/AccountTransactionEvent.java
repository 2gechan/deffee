package com.gc.bank.common;

import java.math.BigDecimal;

public record AccountTransactionEvent(
        Long memberId,
        Long accountId,
        BigDecimal amount,
        TransactionType type
) {
}
