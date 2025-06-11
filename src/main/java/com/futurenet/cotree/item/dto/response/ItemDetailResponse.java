package com.futurenet.cotree.item.dto.response;

import com.futurenet.cotree.item.domain.ItemDetail;
import com.futurenet.cotree.item.util.DiscountUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemDetailResponse {
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
    private String description;

    public static ItemDetailResponse from(ItemDetail itemDetail) {
        int price = itemDetail.getPrice();
        int discount = itemDetail.getDiscount();
        int salePrice = price - discount;
        int discountRate = DiscountUtils.calculateDiscountRate(price, discount);

        return ItemDetailResponse.builder()
                .id(itemDetail.getId())
                .name(itemDetail.getName())
                .price(price)
                .salePrice(salePrice)
                .discountRate(discountRate)
                .origin(itemDetail.getOrigin())
                .thumbnailImage(itemDetail.getThumbnailImage())
                .brandName(itemDetail.getBrandName())
                .isGreen(itemDetail.getIsGreen())
                .quantity(itemDetail.getQuantity())
                .description(itemDetail.getDescription())
                .build();
    }
}
