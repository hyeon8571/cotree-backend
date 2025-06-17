package com.futurenet.cotree.admin.dto.response;

import com.futurenet.cotree.item.constants.ItemClassification;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EcoPurchaseCountResponse {
    private ItemClassification itemClassification;
    private int count;
}
