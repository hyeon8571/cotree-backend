package com.futurenet.cotree.admin.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class PointStat {
    private String statDate;
    private long used;
    private long rewarded;
}
