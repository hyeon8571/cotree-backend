package com.futurenet.cotree.order.repository;

import com.futurenet.cotree.order.dto.OrderItemDto;
import com.futurenet.cotree.order.dto.request.OrderItemRegisterRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderItemRepository {
    int saveOrderItem(@Param("request") OrderItemRegisterRequest request);
    List<OrderItemDto> getAllOrderItemsByOrderIds(List<Long> orderIds);
}
