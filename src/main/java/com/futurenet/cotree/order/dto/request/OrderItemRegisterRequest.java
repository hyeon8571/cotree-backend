package com.futurenet.cotree.order.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemRegisterRequest {
    private Long itemId;;
    private int amount;
}
