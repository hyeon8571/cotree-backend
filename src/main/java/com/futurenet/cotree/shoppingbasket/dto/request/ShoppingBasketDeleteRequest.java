package com.futurenet.cotree.shoppingbasket.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ShoppingBasketDeleteRequest {
    private Long memberId;
    private List<Long> itemIds;
}
