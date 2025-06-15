package com.futurenet.cotree.order.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RegisterOrderResponse {
    private Long orderId;
    private String orderNumber;
}
