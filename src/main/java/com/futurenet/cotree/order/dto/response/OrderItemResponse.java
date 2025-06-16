package com.futurenet.cotree.order.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class OrderItemResponse {
    private Long itemId;
    private String itemName;
    private String itemThumbnailImage;
    private String isGreen;
    private int price;
    private int discount;
    private int quantity;
}
