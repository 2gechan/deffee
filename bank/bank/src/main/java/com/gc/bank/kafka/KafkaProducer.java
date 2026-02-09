package com.gc.bank.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.UUID;

@Component
public class KafkaProducer {

    private static final String BANK_EVENT_TOPIC = "bank.event";
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void send(DomainEvent event) {
        kafkaTemplate.send(
                BANK_EVENT_TOPIC,
                UUID.randomUUID().toString(),
                event
        );
    }
}
