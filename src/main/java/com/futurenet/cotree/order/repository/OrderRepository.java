package com.futurenet.cotree.order.repository;

import com.futurenet.cotree.order.dto.request.OrderRegisterRequest;
import com.futurenet.cotree.order.dto.response.MemberOrderStatusResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderRepository {
    int saveOrder(@Param("request") OrderRegisterRequest request);
    int updateOrderStatus(@Param("id") Long id, @Param("status") String status);
    List<MemberOrderStatusResponse> getOrderStatus(@Param("memberId") Long memberId);
}
