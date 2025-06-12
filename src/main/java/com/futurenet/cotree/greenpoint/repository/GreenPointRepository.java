package com.futurenet.cotree.greenpoint.repository;

import com.futurenet.cotree.greenpoint.dto.GreenPointSaveRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GreenPointRepository {
    int getPoint(@Param("memberId") Long memberId);
    int savePointUsageLog(@Param("memberId") Long memberId, @Param("amount") int amount);
    int savePoint(@Param("request") GreenPointSaveRequest greenPointSaveRequest);
}
