package com.futurenet.cotree.order.service;

import com.futurenet.cotree.order.dto.request.OrderItemRegisterRequest;

import java.util.List;

public interface OrderItemService {
    void registerOrderItem(List<OrderItemRegisterRequest> orderItemRegisterRequests, Long orderId);
}
