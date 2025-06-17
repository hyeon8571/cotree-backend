package com.futurenet.cotree.member.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberAge {
    TEENAGE("10s"),
    TWENTY("20s"),
    THIRTY("30s"),
    FORTY("40s"),
    FIFTY("50s"),
    SIXTY("60s");

    private final String value;
}
