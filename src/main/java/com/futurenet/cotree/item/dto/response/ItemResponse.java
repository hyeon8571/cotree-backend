package com.futurenet.cotree.item.dto.response;

import com.futurenet.cotree.item.domain.Item;
import com.futurenet.cotree.item.util.DiscountUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemResponse {

    private Long id;
    private String name;
    private int price;
    private int salePrice;// 실제 판매 금액 (price - discount)
    private int discountRate;// 할인율 (percent)
    private String origin;
    private String thumbnailImage;
    private String brandName;
    private String isGreen;
    private int quantity;

    public static ItemResponse from(Item item) {
        int price = item.getPrice();
        int discount = item.getDiscount();
        int salePrice = price - discount;
        int discountRate = DiscountUtils.calculateDiscountRate(price, discount);

        return ItemResponse.builder()
                .id(item.getId())
                .name(item.getName())
                .price(price)
                .salePrice(salePrice)
                .discountRate(discountRate)
                .origin(item.getOrigin())
                .thumbnailImage(item.getThumbnailImage())
                .brandName(item.getBrandName())
                .isGreen(item.getIsGreen())
                .quantity(item.getQuantity())
                .build();
    }
}
