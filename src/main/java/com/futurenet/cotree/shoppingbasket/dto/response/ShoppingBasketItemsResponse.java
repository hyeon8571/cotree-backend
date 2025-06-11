package com.futurenet.cotree.shoppingbasket.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ShoppingBasketItemsResponse {
    private Long basketItemId;
    private Long itemId;
    private int quantity;
    private String itemName;
    private int price;
    private int discount;
    private String isGreen;
    private String thumbnailImage;
    private String brandName;
}
