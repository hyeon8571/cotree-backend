package com.futurenet.cotree.order.async.service;

import com.futurenet.cotree.order.dto.request.OrderRequest;

public interface OrderAsyncProcessorService {
    String process(OrderRequest request, Long memberId);
}
