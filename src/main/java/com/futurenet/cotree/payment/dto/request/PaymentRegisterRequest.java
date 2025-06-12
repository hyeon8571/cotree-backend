package com.futurenet.cotree.payment.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class PaymentRegisterRequest {
    private Long paymentId;
    private Long orderId;
    private String cardNumber;
    private String bank;
    private int price;

    public static PaymentRegisterRequest of(PaymentRequest paymentRequest, int price) {
        return PaymentRegisterRequest.builder()
                .orderId(paymentRequest.getOrderId())
                .cardNumber(paymentRequest.getCardNumber())
                .bank(paymentRequest.getBank())
                .price(price)
                .build();
    }
}
