package com.futurenet.cotree.order.service;

import com.futurenet.cotree.item.service.ItemService;
import com.futurenet.cotree.order.dto.request.OrderItemRegisterRequest;
import com.futurenet.cotree.order.dto.request.OrderRegisterRequest;
import com.futurenet.cotree.order.dto.request.OrderRequest;
import com.futurenet.cotree.payment.dto.request.PaymentRequestEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderFacadeServiceImpl implements OrderFacadeService {

    private final OrderService orderService;
    private final ItemService itemService;
    private final OrderItemService orderItemService;
    private final ApplicationEventPublisher eventPublisher;

    /**
     * 사용자의 주문 요청을 처리합니다.
     * 1. 각 주문 상품의 재고를 차감합니다.
     * 2. 주문 정보를 저장합니다.
     * 3. 주문 상품 정보를 저장합니다.
     * 주문 완료 이벤트를 발행하여 결제 처리를 트리거합니다.
     *
     * @param orderRequest 사용자의 주문 요청 정보
     * */
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

        eventPublisher.publishEvent(PaymentRequestEvent.of(orderId, orderRequest));
    }
}
