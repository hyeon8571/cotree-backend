package com.futurenet.cotree.shoppingbasket.dto.request;

import com.futurenet.cotree.order.dto.request.OrderItemRegisterRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ShoppingBasketDeleteRequestEvent {
    Long memberId;
    List<OrderItemRegisterRequest> orderItems;
}
