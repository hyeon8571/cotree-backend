package com.futurenet.cotree.tree.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class GiveWaterResponse {
    private final Integer exp;
    private final Integer remainingWaterUnit;

    public static GiveWaterResponse of(int exp, int remainingWaterUnit) {
        return GiveWaterResponse.builder()
                .exp(exp)
                .remainingWaterUnit(remainingWaterUnit)
                .build();
    }
}
