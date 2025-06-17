package com.futurenet.cotree.admin.repository;

import com.futurenet.cotree.admin.domain.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminRepository {
    Admin getAdminByLoginId(@Param("loginId") String loginId);
    Admin getAdminById(@Param("id") Long id);
}
