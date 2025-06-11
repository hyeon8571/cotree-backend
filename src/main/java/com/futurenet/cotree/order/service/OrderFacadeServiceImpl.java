package com.futurenet.cotree.order.service;

import com.futurenet.cotree.item.service.ItemService;
import com.futurenet.cotree.order.dto.request.OrderItemRegisterRequest;
import com.futurenet.cotree.order.dto.request.OrderRegisterRequest;
import com.futurenet.cotree.order.dto.request.OrderRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderFacadeServiceImpl implements OrderFacadeService {

    private final OrderService orderService;
    private final ItemService itemService;
    private final OrderItemService orderItemService;

    @Override
    @Transactional
    public void registerOrder(OrderRequest orderRequest, Long memberId) {

        // TODO: 반복문 내의 단발성 쿼리 Bulk 처리하기

        for (OrderItemRegisterRequest item : orderRequest.getOrderItems()) {
            itemService.decreaseStock(item.getItemId(), item.getQuantity());
        }

        OrderRegisterRequest orderRegisterRequest = OrderRegisterRequest.from(orderRequest);
        orderRegisterRequest.setMemberId(memberId);

        Long orderId = orderService.registerOrderRequest(orderRegisterRequest);

        for (OrderItemRegisterRequest item : orderRequest.getOrderItems()) {
            item.setOrderId(orderId);
            orderItemService.registerOrderItem(item);
        }
    }
}
