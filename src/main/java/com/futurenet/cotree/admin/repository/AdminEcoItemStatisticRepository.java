package com.futurenet.cotree.admin.repository;

import com.futurenet.cotree.admin.dto.response.EcoPurchaseCategoryResponse;
import com.futurenet.cotree.admin.dto.response.PopularEcoItemResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminEcoItemStatisticRepository {
    int getEcoOrderItemCount();
    int getOrderItemCount();
    List<PopularEcoItemResponse> getPopularEcoItems();
    List<EcoPurchaseCategoryResponse> getPurchaseByCategory();
}
