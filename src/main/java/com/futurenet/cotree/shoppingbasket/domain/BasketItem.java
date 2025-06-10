package com.futurenet.cotree.shoppingbasket.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BasketItem {
    private Long id;
    private Long shoppingBasketId;
    private Long itemId;
    private int quantity;
    private LocalDateTime createdAt;
}
