package com.futurenet.cotree.admin.repository;

import com.futurenet.cotree.member.constant.MemberGender;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminEcoItemStatisticRepository {
    int getEcoOrderItemCount();
    int getGeneralOrderItemCount();
    int getEcoOrderItemCountByAge(@Param("age") String age);
    int getEcoOrderItemCountByGender(@Param("gender") MemberGender gender);
}
