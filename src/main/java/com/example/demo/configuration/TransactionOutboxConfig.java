package com.example.demo.configuration;


import com.gruelbox.transactionoutbox.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.Duration;

@Configuration
@EnableScheduling
@Import({ SpringTransactionOutboxConfiguration.class })
public class TransactionOutboxConfig {
    @Bean
    public TransactionOutbox transactionOutbox(SpringTransactionManager springTransactionManager,
                                               SpringInstantiator springInstantiator) {
        return TransactionOutbox.builder()
                .instantiator(springInstantiator)
                .initializeImmediately(true)
                .retentionThreshold(Duration.ofMinutes(5))
                .attemptFrequency(Duration.ofSeconds(30))
                .blockAfterAttempts(5)
                .transactionManager(springTransactionManager)
                .persistor(Persistor.forDialect(Dialect.POSTGRESQL_9))
                .build();
    }
}
