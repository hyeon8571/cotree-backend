package com.futurenet.cotree.item.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDto {
    private Long id;
    private String name;
    private int price;
    private int discount;
    private String origin;
    private String thumbnailImage;
    private String brandName;
    private String isGreen;
}
