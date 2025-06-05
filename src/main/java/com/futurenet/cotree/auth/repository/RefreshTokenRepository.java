package com.futurenet.cotree.auth.repository;

import com.futurenet.cotree.auth.dto.request.RefreshTokenSaveRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RefreshTokenRepository {
    void saveRefreshToken(@Param("request") RefreshTokenSaveRequest request);
    String getRefreshTokenByMemberId(Long memberId);
}
