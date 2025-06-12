package com.futurenet.cotree.tree.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MyTreeResponse {
    private int exp;
    private int remainingWaterUnit;

    public static MyTreeResponse of(int exp, int remainingWaterUnit) {
        return new MyTreeResponse(exp, remainingWaterUnit);
    }
}