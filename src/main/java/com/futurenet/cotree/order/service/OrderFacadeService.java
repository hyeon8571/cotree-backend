package com.futurenet.cotree.order.service;

import com.futurenet.cotree.order.dto.request.OrderRequest;
import com.futurenet.cotree.order.dto.response.OrderDetailResponse;
import com.futurenet.cotree.order.dto.response.OrderResponse;

import java.util.List;

public interface OrderFacadeService {
    String registerOrder(OrderRequest orderRequest, Long memberId);
    List<OrderResponse> getOrdersByMember(Long memberId, String status, int page);
    OrderDetailResponse getOrderDetail(String orderNumber, Long memberId);
    String registerOrders(OrderRequest orderRequest, Long memberId);
    String registerEventOrder(OrderRequest orderRequest, Long memberId);
    String registerOrderV2(OrderRequest orderRequest, Long memberId);
    String registerOrderV3(OrderRequest orderRequest, Long memberId);
}
