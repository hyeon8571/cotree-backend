package com.futurenet.cotree.order.repository;

import com.futurenet.cotree.order.dto.request.OrderRegisterRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderRepository {
    int saveOrder(@Param("request") OrderRegisterRequest request);
    int updateOrderStatus(@Param("id") Long id, String status);
}
