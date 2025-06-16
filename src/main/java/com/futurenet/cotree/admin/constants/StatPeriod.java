package com.futurenet.cotree.admin.constants;

import com.futurenet.cotree.admin.service.exception.AdminErrorCode;
import com.futurenet.cotree.admin.service.exception.AdminException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum StatPeriod {
    WEEK("7d", 7),
    MONTH("30d", 30),
    QUARTER("90d", 90);

    private final String key;
    private final int days;

    StatPeriod(String key, int days) {
        this.key = key;
        this.days = days;
    }

    public static StatPeriod from(String key) {
        return Arrays.stream(values())
                .filter(p -> p.key.equals(key))
                .findFirst()
                .orElseThrow(() -> new AdminException(AdminErrorCode.INVALID_RANGE));
    }


}
