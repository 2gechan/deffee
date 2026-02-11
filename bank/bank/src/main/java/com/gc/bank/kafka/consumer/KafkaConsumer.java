package com.gc.bank.kafka.consumer;

import com.gc.bank.domains.transaction.repository.TransactionLogRepository;
import com.gc.bank.domains.transaction.event.AccountTransactionEvent;
import com.gc.bank.domains.transaction.event.DomainEvent;
import com.gc.bank.types.entity.AccountTransactionLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

    private final TransactionLogRepository logRepository;

    public KafkaConsumer(TransactionLogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @KafkaListener(topics = "bank.event", groupId = "bank-log")
    public void consume(DomainEvent event) {
        if (event instanceof AccountTransactionEvent e) {

            log.info(
                    "거래 발생 : member={}, account={}, type={}, amount={}"
                    , e.memberId(), e.accountId(), e.type(), e.amount()
            );

            logRepository.save(new AccountTransactionLog(e.memberId(), e.accountId(), e.type(), e.amount(), e.occurredAt()));

        }
    }
}
