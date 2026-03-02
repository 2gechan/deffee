package com.gc.bank.types.entity;


import com.gc.bank.domains.transaction.event.TransactionType;
import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Getter
public class AccountTransactionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long memberId;
    private Long accountId;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    private BigDecimal amount;
    private Instant occurredAt;

    protected AccountTransactionLog() {
    }

    public AccountTransactionLog(Long memberId, Long accountId, TransactionType type, BigDecimal amount, Instant occurredAt) {
        this.id = id;
        this.memberId = memberId;
        this.accountId = accountId;
        this.type = type;
        this.amount = amount;
        this.occurredAt = occurredAt;
    }
}
