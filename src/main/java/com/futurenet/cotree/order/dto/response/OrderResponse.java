package com.futurenet.cotree.order.dto.response;

import com.futurenet.cotree.order.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class OrderResponse {
    private Long orderId;
    private LocalDateTime orderDate;
    private String orderNumber;
    private List<OrderItemResponse> orderItemResponses;

    public static OrderResponse of(Order order, List<OrderItemResponse> orderItemResponses) {
        return OrderResponse.builder()
                .orderId(order.getId())
                .orderDate(order.getOrderDate())
                .orderNumber(order.getOrderNumber())
                .orderItemResponses(orderItemResponses)
                .build();
    }
}
