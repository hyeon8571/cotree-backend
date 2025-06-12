package com.futurenet.cotree.payment.handler;

import com.futurenet.cotree.payment.dto.request.PaymentRequestEvent;
import com.futurenet.cotree.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class PaymentEventHandler {
    private final PaymentService paymentService;

    @Async
    @TransactionalEventListener
    public void handleEvent(PaymentRequestEvent paymentRequestEvent) {
        paymentService.pay(paymentRequestEvent.toDto());
    }
}
