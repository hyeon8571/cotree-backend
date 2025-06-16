package com.futurenet.cotree.order.service;

import com.futurenet.cotree.order.dto.OrderItemDto;
import com.futurenet.cotree.order.dto.request.OrderItemRegisterRequest;

import java.util.List;

public interface OrderItemService {
    void registerOrderItem(OrderItemRegisterRequest orderItemRegisterRequest);
    List<OrderItemDto> getAllOrderItemsByOrderIds(List<Long> orderIds);
}
