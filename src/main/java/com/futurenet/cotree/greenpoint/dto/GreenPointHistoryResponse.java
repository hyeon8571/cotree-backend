package com.futurenet.cotree.greenpoint.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.futurenet.cotree.greenpoint.domain.GreenPoint;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class GreenPointHistoryResponse {
    private Long id;
    private int amount;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdAt;

    public static GreenPointHistoryResponse from(GreenPoint entity) {
        return GreenPointHistoryResponse.builder()
                .id(entity.getId())
                .amount(entity.getAmount())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}