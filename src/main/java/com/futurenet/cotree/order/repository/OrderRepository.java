package com.futurenet.cotree.order.repository;

import com.futurenet.cotree.order.dto.request.OrderRegisterRequest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderRepository {
    int saveOrder(OrderRegisterRequest orderRegisterRequest);
}
