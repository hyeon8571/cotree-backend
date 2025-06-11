package com.futurenet.cotree.order.repository;

import com.futurenet.cotree.order.dto.request.OrderItemRegisterRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderItemRepository {
    int saveOrderItem(@Param("request") OrderItemRegisterRequest request);
}
