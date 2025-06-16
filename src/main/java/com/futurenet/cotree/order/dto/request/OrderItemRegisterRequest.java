package com.futurenet.cotree.order.dto.request;

import com.futurenet.cotree.order.domain.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class OrderItemRegisterRequest {
    private Long itemId;;
    private Long orderId;
    private int quantity;

    public static OrderItemRegisterRequest from(OrderItem orderItem) {
        return OrderItemRegisterRequest.builder()
                .itemId(orderItem.getItemId())
                .orderId(orderItem.getOrderId())
                .quantity(orderItem.getQuantity())
                .build();
    }
}
