package com.futurenet.cotree.order.service;

import com.futurenet.cotree.order.dto.request.OrderRegisterRequest;
import com.futurenet.cotree.order.dto.response.RegisterOrderResponse;
import com.futurenet.cotree.order.repository.OrderRepository;
import com.futurenet.cotree.order.service.exception.OrderErrorCode;
import com.futurenet.cotree.order.service.exception.OrderException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    @Transactional
    public RegisterOrderResponse registerOrderRequest(OrderRegisterRequest orderRegisterRequest) {

        String orderNumber = createOrderNumber(orderRegisterRequest.getMemberId());
        orderRegisterRequest.setOrderNumber(orderNumber);
        int result = orderRepository.saveOrder(orderRegisterRequest);

        if (result == 0) {
            throw new OrderException(OrderErrorCode.ORDER_REGISTER_FAIL);
        }

        return new RegisterOrderResponse(orderRegisterRequest.getOrderId(), orderNumber);
    }

    private String createOrderNumber(Long memberId) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        int rand = (int)(System.nanoTime() % 900) + 100;
        long value = memberId * rand % 999999;

        return String.format("%s-%06d", timestamp, value);
    }
}
