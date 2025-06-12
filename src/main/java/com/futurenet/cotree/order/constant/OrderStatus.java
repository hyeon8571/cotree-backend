package com.futurenet.cotree.order.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {

    SUCCESS("paid"), FAIL("failed");

    private final String status;
}
