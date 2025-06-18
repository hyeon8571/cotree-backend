package com.futurenet.cotree.payment.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPriceAndIsEcoResponse {
    private Long id;
    private int price;
    private int discount;
    private String isGreen;
}
