package com.futurenet.cotree.greenpoint.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.futurenet.cotree.greenpoint.domain.GreenPoint;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class GreenPointHistoryResponse {
    private int remainPoint;
    private int totalCount;
    private List<History> history;

    @Getter
    @Builder
    public static class History {
        private Long id;
        private int amount;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
        private LocalDateTime createdAt;

        public static History from(GreenPoint entity) {
            return History.builder()
                    .id(entity.getId())
                    .amount(entity.getAmount())
                    .createdAt(entity.getCreatedAt())
                    .build();
        }
    }

    public static GreenPointHistoryResponse of(List<GreenPoint> points, int remainPoint, int totalCount) {
        List<History> list = points.stream()
                .map(History::from)
                .toList();

        return GreenPointHistoryResponse.builder()
                .remainPoint(remainPoint)
                .totalCount(totalCount)
                .history(list)
                .build();
    }
}
