package com.futurenet.cotree.order.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Order {
    private Long id;
    private String orderNumber;
    private LocalDateTime orderDate;
    private String destination;
    private String receiverName;
    private String receiverTel;
    private String request;
}
