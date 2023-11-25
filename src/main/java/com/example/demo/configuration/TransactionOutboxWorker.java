package com.example.demo.configuration;

import com.gruelbox.transactionoutbox.TransactionOutbox;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TransactionOutboxWorker {
    private final TransactionOutbox transactionOutbox;

    @Scheduled(fixedDelay = 5000)
    public void flushTransactionOutbox() {
        transactionOutbox.flush();
    }
}
