package com.futurenet.cotree.admin.repository;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminEcoItemStatisticRepository {
    int getEcoOrderItemCount();
    int getOrderItemCount();
}
