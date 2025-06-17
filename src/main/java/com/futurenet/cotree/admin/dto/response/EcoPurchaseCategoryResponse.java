package com.futurenet.cotree.admin.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EcoPurchaseCategoryResponse {
    private String categoryName;
    private int normalItemPurchaseCount;
    private int ecoItemPurchaseCount;
}
