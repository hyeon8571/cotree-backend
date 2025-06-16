package com.futurenet.cotree.order.dto;

import com.futurenet.cotree.order.dto.response.OrderItemResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDto {
    private Long orderId;
    private Long itemId;
    private int quantity;
    private String itemName;
    private String itemThumbnailImage;
    private String isGreen;
    private int price;

    public OrderItemResponse toResponse() {
        return OrderItemResponse.builder()
                .itemId(this.itemId)
                .itemName(this.itemName)
                .itemThumbnailImage(this.itemThumbnailImage)
                .isGreen(this.isGreen)
                .price(this.price)
                .quantity(this.quantity)
                .build();
    }
}
