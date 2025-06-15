package com.futurenet.cotree.order.service;

import com.futurenet.cotree.order.dto.request.OrderRequest;

public interface OrderFacadeService {
    String registerOrder(OrderRequest orderRequest, Long memberId);
}
