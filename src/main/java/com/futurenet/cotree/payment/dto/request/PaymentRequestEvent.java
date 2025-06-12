package com.futurenet.cotree.payment.dto.request;

import com.futurenet.cotree.order.dto.request.OrderItemRegisterRequest;
import com.futurenet.cotree.order.dto.request.OrderRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class PaymentRequestEvent {
    private Long orderId;
    private String cardNumber;
    private String bank;
    private List<OrderItemRegisterRequest> orderItems;

    public static PaymentRequestEvent of(Long orderId, OrderRequest orderRequest) {
        return PaymentRequestEvent.builder()
                .orderId(orderId)
                .cardNumber(orderRequest.getCardNumber())
                .bank(orderRequest.getBankName())
                .orderItems(orderRequest.getOrderItems())
                .build();
    }

    public PaymentRequest toDto() {
        return PaymentRequest.builder()
                .orderId(this.orderId)
                .cardNumber(this.cardNumber)
                .bank(this.bank)
                .orderItems(this.orderItems)
                .build();
    }
}
