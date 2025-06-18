package com.futurenet.cotree.order.service;

import com.futurenet.cotree.order.dto.OrderItemDto;
import com.futurenet.cotree.order.dto.request.OrderItemRegisterRequest;
import com.futurenet.cotree.order.dto.response.OrderItemResponse;
import com.futurenet.cotree.order.repository.OrderItemRepository;
import com.futurenet.cotree.order.service.exception.OrderErrorCode;
import com.futurenet.cotree.order.service.exception.OrderException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;

    @Override
    @Transactional
    public List<OrderItemDto> getAllOrderItemsByOrderIds(List<Long> orderIds) {
        return orderItemRepository.getAllOrderItemsByOrderIds(orderIds);
    }

    @Override
    @Transactional
    public List<OrderItemResponse> getOrderItemsByOrderId(Long orderId) {
        return orderItemRepository.getOrderItemsWithItemInfoByOrderId(orderId);
    }

    @Override
    @Transactional
    public void registerOrderItems(Long orderId, List<OrderItemRegisterRequest> orderItemRegisterRequests) {
        int result = orderItemRepository.saveOrderItems(orderId, orderItemRegisterRequests);

        if (result != orderItemRegisterRequests.size()) {
            throw new OrderException(OrderErrorCode.ORDER_ITEM_REGISTER_FAIL);
        }
    }
}
