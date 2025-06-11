package com.futurenet.cotree.tree.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TreeRepository {
    void saveTree(Long memberId);
    Integer getMyTree(@Param("memberId") Long memberId);
}
