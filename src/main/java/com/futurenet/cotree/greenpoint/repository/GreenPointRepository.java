package com.futurenet.cotree.greenpoint.repository;

import com.futurenet.cotree.greenpoint.domain.GreenPoint;
import com.futurenet.cotree.greenpoint.dto.GreenPointHistoryResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GreenPointRepository {
    int getPoint(@Param("memberId") Long memberId);
    int savePointUsageLog(@Param("memberId") Long memberId, @Param("amount") int amount);
    List<GreenPoint> getPointHistory(@Param("memberId") Long memberId, @Param("page") int page, @Param("size") int size);
}
