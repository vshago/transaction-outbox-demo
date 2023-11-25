package com.example.demo.service;

import com.example.demo.dao.OrderEntity;
import com.example.demo.dao.OrderRepository;
import com.gruelbox.transactionoutbox.TransactionOutbox;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Service
@AllArgsConstructor
@Slf4j
public class OrderService {

    private OrderRepository repository;
    private TransactionOutbox outbox;

    @Transactional
    public String createOrderAndSendEvent(Integer productId, Integer quantity) {
        String uuid = UUID.randomUUID().toString();
        repository.save(new OrderEntity(uuid, productId, quantity));
        outbox.schedule(getClass()).sendOrderEvent(uuid, productId, quantity);
        return uuid;
    }

    void sendOrderEvent(String uuid, Integer productId, Integer quantity) {
        log.info(String.format("Sending event for %s...", uuid));
        if (ThreadLocalRandom.current().nextBoolean())
            throw new RuntimeException();

        log.info(String.format("Event sent for %s", uuid));
    }
}
