package com.futurenet.cotree.admin.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PopularEcoItemResponse {
    private Long itemId;
    private String itemName;
    private int purchaseCount;
}
