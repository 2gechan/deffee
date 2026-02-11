package com.gc.bank.types.entity;


import com.gc.bank.domains.transaction.event.TransactionType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
public class AccountTransactionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long member_id;
    private Long account_id;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    private BigDecimal amount;
    private Instant occurred_at;

    protected AccountTransactionLog() {
    }

    public AccountTransactionLog(Long member_id, Long account_id, TransactionType type, BigDecimal amount, Instant occurred_at) {
        this.member_id = member_id;
        this.account_id = account_id;
        this.type = type;
        this.amount = amount;
        this.occurred_at = occurred_at;
    }
}
