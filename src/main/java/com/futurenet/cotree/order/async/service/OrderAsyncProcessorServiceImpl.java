package com.futurenet.cotree.order.async.service;

import com.futurenet.cotree.order.dto.request.OrderRequest;
import com.futurenet.cotree.order.service.OrderFacadeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderAsyncProcessorServiceImpl implements OrderAsyncProcessorService {

    private final OrderFacadeService orderServiceFacade;

    @Override
    public String process(OrderRequest orderRequest, Long memberId) {
        return orderServiceFacade.registerOrders(orderRequest, memberId);
    }


}
