package com.futurenet.cotree.payment.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentConfirmRequest {
    private String orderNumber;
    private String bank;
    private String cardNumber;
}
