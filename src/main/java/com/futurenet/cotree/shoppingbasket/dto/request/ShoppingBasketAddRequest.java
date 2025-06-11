package com.futurenet.cotree.shoppingbasket.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShoppingBasketAddRequest {
    private Long itemId;
    private Integer quantity;
}
