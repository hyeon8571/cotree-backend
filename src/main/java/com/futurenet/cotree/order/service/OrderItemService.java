package com.futurenet.cotree.order.service;

import com.futurenet.cotree.order.dto.OrderItemDto;
import com.futurenet.cotree.order.dto.request.OrderItemRegisterRequest;
import com.futurenet.cotree.order.dto.response.OrderItemResponse;

import java.util.List;

public interface OrderItemService {
    List<OrderItemDto> getAllOrderItemsByOrderIds(List<Long> orderIds);
    List<OrderItemResponse> getOrderItemsByOrderId(Long orderId);
    void registerOrderItems(Long orderId, List<OrderItemRegisterRequest> orderItemRegisterRequests);
}
