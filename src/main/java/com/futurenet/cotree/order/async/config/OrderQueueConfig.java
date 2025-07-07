package com.futurenet.cotree.order.async.config;

import com.futurenet.cotree.order.async.dto.request.OrderRequestWithMember;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Configuration
public class OrderQueueConfig {
    @Bean
    public BlockingQueue<OrderRequestWithMember> orderRequestQueue() {
        return new LinkedBlockingQueue<>();
    }
}
