package com.futurenet.cotree.statistic.ecoItem.repository;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EcoItemStatisticRepository {
    int getEcoOrderItemCount();
    int getOrderItemCount();
}
