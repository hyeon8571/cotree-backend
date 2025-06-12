package com.futurenet.cotree.payment.dto.request;

import com.futurenet.cotree.order.dto.request.OrderItemRegisterRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class PaymentRequest {
    private Long memberId;
    private Long orderId;
    private String cardNumber;
    private String bank;
    private List<OrderItemRegisterRequest> orderItems;
}
