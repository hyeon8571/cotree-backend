package com.futurenet.cotree.order.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class OrderRegisterRequest {
    private Long memberId;
    private String orderNumber;
    private String destination;
    private String receiverName;
    private String receiverTel;
    private String request;

    public static OrderRegisterRequest from(OrderRequest orderRequest) {
        return OrderRegisterRequest.builder()
                .destination(orderRequest.getDestination())
                .request(orderRequest.getRequest())
                .receiverName(orderRequest.getReceiverName())
                .receiverTel(orderRequest.getReceiverTel())
                .build();
    }
}
