package com.futurenet.cotree.order.async.cunsumer;

import com.futurenet.cotree.order.async.dto.request.OrderRequestWithMember;
import com.futurenet.cotree.order.async.service.OrderAsyncProcessorService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderRequestConsumer implements Runnable {

    private final BlockingQueue<OrderRequestWithMember> orderRequestQueue;
    private final OrderAsyncProcessorService orderAsyncProcessorService;

    private final AtomicInteger totalRequestCount = new AtomicInteger(0);
    private final AtomicInteger completedCount = new AtomicInteger(0);
    private long batchStartTime = 0;

    public void addRequest(OrderRequestWithMember request) {
        orderRequestQueue.offer(request);
        if (totalRequestCount.get() == 0) batchStartTime = System.currentTimeMillis();
        totalRequestCount.incrementAndGet();
    }

    @PostConstruct
    public void startConsumerThread() {
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(this, "OrderRequestConsumer-" + i);
            thread.start();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                OrderRequestWithMember request = orderRequestQueue.take();
                String orderNumber = orderAsyncProcessorService.process(
                        request.getOrderRequest(),
                        request.getMemberId()
                );
                long elapsed = System.currentTimeMillis() - request.getEnqueueTime();

                if (completedCount.incrementAndGet() == totalRequestCount.get()) {
                    long totalElapsed = System.currentTimeMillis() - batchStartTime;
                    log.info("🎉 전체 주문 처리 완료! 총 처리 시간: {}ms", totalElapsed);
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            } catch (Exception e) {
                log.error("🔥 주문 처리 중 예외 발생", e);
            }
        }
    }

    public int getTotalRequestCount() {
        return totalRequestCount.get();
    }

    public int getCompletedCount() {
        return completedCount.get();
    }

    public int getQueueSize() {
        return orderRequestQueue.size();
    }
}
