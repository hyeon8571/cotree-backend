package com.futurenet.cotree.order.service;

import com.futurenet.cotree.order.dto.request.OrderItemRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    @Override
    public void registerOrderItem(List<OrderItemRegisterRequest> orderItemRegisterRequests, Long orderId) {

    }
}
