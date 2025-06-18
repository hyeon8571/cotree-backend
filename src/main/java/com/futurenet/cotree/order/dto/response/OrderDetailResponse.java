package com.futurenet.cotree.order.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderDetailResponse {
    private Long memberId;
    private Long orderId;
    private String orderNumber;
    private String destination;
    private String receiverName;
    private String receiverTel;
    private String request;
    private String status;
    private int totalPrice;
    private int rewardGreenPoint;
    List<OrderItemResponse> orderItems;
}
