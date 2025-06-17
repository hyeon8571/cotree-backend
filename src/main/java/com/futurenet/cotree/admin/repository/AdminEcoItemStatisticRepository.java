package com.futurenet.cotree.admin.repository;

import com.futurenet.cotree.admin.dto.response.EcoPurchaseCategoryResponse;
import com.futurenet.cotree.admin.dto.response.PopularEcoItemResponse;
import com.futurenet.cotree.member.constant.MemberGender;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminEcoItemStatisticRepository {
    int getEcoOrderItemCount();
    int getGeneralOrderItemCount();
    int getEcoOrderItemCountByAge(@Param("age") String age);
    int getEcoOrderItemCountByGender(@Param("gender") MemberGender gender);
    List<PopularEcoItemResponse> getPopularEcoItems();
    List<EcoPurchaseCategoryResponse> getPurchaseByCategory();
}
