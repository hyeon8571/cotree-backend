package com.futurenet.cotree.item.dto.response;

import com.futurenet.cotree.item.dto.ItemDto;
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

    public static ItemResponse from(ItemDto itemDto) {
        int price = itemDto.getPrice();
        int discount = itemDto.getDiscount();
        int salePrice = price - discount;
        int discountRate = calculateDiscountRate(price, discount);

        return ItemResponse.builder()
                .id(itemDto.getId())
                .name(itemDto.getName())
                .price(price)
                .salePrice(salePrice)
                .discountRate(discountRate)
                .origin(itemDto.getOrigin())
                .thumbnailImage(itemDto.getThumbnailImage())
                .brandName(itemDto.getBrandName())
                .isGreen(itemDto.getIsGreen())
                .build();
    }

    //할인율 계산
    private static int calculateDiscountRate(int price, int discount) {
        if (price == 0) return 0;
        return (int) Math.round((double) discount / price * 100);
    }
}
