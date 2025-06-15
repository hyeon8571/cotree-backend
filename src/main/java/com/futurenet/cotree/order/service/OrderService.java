package com.futurenet.cotree.order.service;

import com.futurenet.cotree.order.dto.request.OrderRegisterRequest;
import com.futurenet.cotree.order.dto.response.RegisterOrderResponse;

public interface OrderService {
    RegisterOrderResponse registerOrderRequest(OrderRegisterRequest orderRegisterRequest);
}
