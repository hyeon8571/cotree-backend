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
    public void registerOrderItem(OrderItemRegisterRequest orderItemRegisterRequest) {
        int result = orderItemRepository.saveOrderItem(orderItemRegisterRequest);

        if (result == 0) {
            throw new OrderException(OrderErrorCode.ORDER_ITEM_REGISTER_FAIL);
        }
    }

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
}
