package com.futurenet.cotree.greenpoint.dto;

import com.futurenet.cotree.greenpoint.constant.GreenPointPolicy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class GreenPointSaveRequest {
    private Long memberId;
    private Long paymentId;
    private long amount;

    public static GreenPointSaveRequest of(Long memberId, Long paymentId, long price) {
        return GreenPointSaveRequest.builder()
                .memberId(memberId)
                .paymentId(paymentId)
                .amount((long) (price * GreenPointPolicy.GREEN_POINT_RATE))
                .build();
    }
}
