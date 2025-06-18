package com.futurenet.cotree.order.repository;

import com.futurenet.cotree.order.domain.OrderItem;
import com.futurenet.cotree.order.dto.OrderItemDto;
import com.futurenet.cotree.order.dto.request.OrderItemRegisterRequest;
import com.futurenet.cotree.order.dto.response.OrderItemResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface OrderItemRepository {
    int saveOrderItem(@Param("request") OrderItemRegisterRequest request);
    List<OrderItemDto> getAllOrderItemsByOrderIds(List<Long> orderIds);
    int getEcoItemPurchaseCountThisMonth(@Param("memberId") Long memberId, @Param("start") LocalDate start, @Param("end") LocalDate end);
    List<OrderItemResponse> getOrderItemsWithItemInfoByOrderId(Long orderId);
    List<OrderItem> getOrderItemsByOrderId(Long orderId);
    int saveOrderItems(@Param("orderId") Long orderId, @Param("request") List<OrderItemRegisterRequest> registerRequests);
}
