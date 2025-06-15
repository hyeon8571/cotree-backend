package com.futurenet.cotree.history.repository;

import com.futurenet.cotree.history.dto.request.MemberActionRequestEvent;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogRepository {
    void saveMemberActionLog(MemberActionRequestEvent event);
}
