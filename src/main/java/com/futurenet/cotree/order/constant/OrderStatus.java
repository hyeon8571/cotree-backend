package com.futurenet.cotree.order.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {

    SUCCESS("PAID"), FAIL("FAILED"), WAITING("PENDING");

    private final String status;
}
