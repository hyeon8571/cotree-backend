package com.futurenet.cotree.member.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum IsSignupCompletedStatus {

    COMPLETED("Y"), NOT_COMPLETED("N");

    private final String status;
}
