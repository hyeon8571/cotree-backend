package com.futurenet.cotree.order.async.config;

import com.futurenet.cotree.order.async.cunsumer.OrderRequestConsumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderRequestMonitor {

    private final OrderRequestConsumer consumer;

    @Scheduled(fixedRate = 5000)
    public void logProgress() {
        int total = consumer.getTotalRequestCount();
        int done = consumer.getCompletedCount();
        int waiting = consumer.getQueueSize();
    }
}