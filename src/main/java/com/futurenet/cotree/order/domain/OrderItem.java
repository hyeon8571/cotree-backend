package com.futurenet.cotree.order.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItem {
    private Long id;
    private Long orderId;
    private Long itemId;
    private int quantity;
}
