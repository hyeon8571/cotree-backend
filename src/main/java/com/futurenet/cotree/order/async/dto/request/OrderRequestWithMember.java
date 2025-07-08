package com.futurenet.cotree.order.async.dto.request;

import com.futurenet.cotree.order.dto.request.OrderRequest;
import lombok.Getter;

@Getter
public class OrderRequestWithMember {
    private final OrderRequest orderRequest;
    private final Long memberId;
    private final long enqueueTime;

    public OrderRequestWithMember(OrderRequest orderRequest, Long memberId) {
        this.orderRequest = orderRequest;
        this.memberId = memberId; this.enqueueTime = System.currentTimeMillis();

    }
}
