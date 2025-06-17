package com.futurenet.cotree.item.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ItemClassification {
    GENERAL("GENERAL"),ECO("ECO");

    private final String value;
}
