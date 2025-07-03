package com.futurenet.cotree.item.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class EventItem {
    private Long itemId;
    private int eventDiscount;
    private LocalDateTime eventStartTime;
    private LocalDateTime eventEndTime;
}
