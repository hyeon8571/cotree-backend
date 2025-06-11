package com.futurenet.cotree.order.service;

import com.futurenet.cotree.order.dto.request.OrderRegisterRequest;
import com.futurenet.cotree.order.dto.request.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderFacadeServiceImpl implements OrderFacadeService {

    private final OrderService orderService;

    @Override
    @Transactional
    public void registerOrder(OrderRequest orderRequest, Long memberId) {

        OrderRegisterRequest orderRegisterRequest = OrderRegisterRequest.from(orderRequest);
        orderRegisterRequest.setMemberId(memberId);

        orderService.registerOrderRequest(orderRegisterRequest);


    }
}
