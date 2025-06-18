package com.futurenet.cotree.order.service;

import com.futurenet.cotree.order.dto.request.OrderRequest;
import com.futurenet.cotree.order.dto.response.OrderDetailResponse;
import com.futurenet.cotree.order.dto.response.OrderResponse;

import java.util.List;

public interface OrderFacadeService {
    String registerOrder(OrderRequest orderRequest, Long memberId);
    List<OrderResponse> getOrdersByMember(Long memberId, String status);
    OrderDetailResponse getOrderDetail(String orderNumber, Long memberId);
}
