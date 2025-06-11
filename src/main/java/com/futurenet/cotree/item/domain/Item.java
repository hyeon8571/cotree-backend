package com.futurenet.cotree.item.domain;

import lombok.Getter;

@Getter
public class Item {
    private Long id;
    private String name;
    private int price;
    private int discount;
    private int quantity;
    private String origin;
    private String thumbnailImage;
    private String brandName;
    private String isGreen;
}
