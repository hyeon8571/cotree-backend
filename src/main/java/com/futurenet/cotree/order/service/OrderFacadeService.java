package com.futurenet.cotree.order.service;

import com.futurenet.cotree.order.dto.request.OrderRequest;

public interface OrderFacadeService {
    void registerOrder(OrderRequest orderRequest, Long memberId);
}
