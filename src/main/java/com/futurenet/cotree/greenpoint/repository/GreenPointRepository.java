package com.futurenet.cotree.greenpoint.repository;

import com.futurenet.cotree.admin.dto.response.PointStat;
import com.futurenet.cotree.greenpoint.domain.GreenPoint;
import com.futurenet.cotree.greenpoint.dto.GreenPointSaveRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface GreenPointRepository {
    int getPoint(@Param("memberId") Long memberId);
    int savePointUsageLog(@Param("memberId") Long memberId, @Param("amount") int amount);
    int countPointHistory(@Param("memberId") Long memberId);
    List<GreenPoint> getPointHistory(@Param("memberId") Long memberId, @Param("page") int page, @Param("size") int size);
    int savePoint(@Param("request") GreenPointSaveRequest greenPointSaveRequest);
    List<PointStat> getPointStatsByRange(@Param("from") LocalDate from, @Param("to") LocalDate to);
}
