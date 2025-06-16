package com.futurenet.cotree.order.service;

import com.futurenet.cotree.order.domain.Order;
import com.futurenet.cotree.order.dto.request.OrderRegisterRequest;
import com.futurenet.cotree.order.dto.response.OrderDetailResponse;
import com.futurenet.cotree.order.dto.response.RegisterOrderResponse;

import java.util.List;

public interface OrderService {
    RegisterOrderResponse registerOrderRequest(OrderRegisterRequest orderRegisterRequest);
    List<Order> getAllOrderByMemberIdAndStatus(Long memberId, String status);
    OrderDetailResponse getOrderByOrderNumber(String orderNumber);
}
