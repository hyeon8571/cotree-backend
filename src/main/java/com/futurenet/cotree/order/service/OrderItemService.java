package com.futurenet.cotree.order.service;

import com.futurenet.cotree.order.dto.request.OrderItemRegisterRequest;

public interface OrderItemService {
    void registerOrderItem(OrderItemRegisterRequest orderItemRegisterRequest);
}
