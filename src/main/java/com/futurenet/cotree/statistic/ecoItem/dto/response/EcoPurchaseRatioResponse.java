package com.futurenet.cotree.statistic.ecoItem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EcoPurchaseRatioResponse {
    private String label;
    private double ratio;
}
