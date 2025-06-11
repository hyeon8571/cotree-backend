package com.futurenet.cotree.order.service;

import com.futurenet.cotree.order.dto.request.OrderRegisterRequest;

public interface OrderService {
    Long registerOrderRequest(OrderRegisterRequest orderRegisterRequest);
}
