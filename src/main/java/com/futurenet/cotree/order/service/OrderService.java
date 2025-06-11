package com.futurenet.cotree.order.service;

import com.futurenet.cotree.order.dto.request.OrderRegisterRequest;

public interface OrderService {
    void registerOrderRequest(OrderRegisterRequest orderRegisterRequest);
}
