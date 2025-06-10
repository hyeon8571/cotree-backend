package com.futurenet.cotree.tree.repository;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TreeRepository {
    void saveTree(Long memberId);
}
