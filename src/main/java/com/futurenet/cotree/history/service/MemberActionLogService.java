package com.futurenet.cotree.history.service;

import com.futurenet.cotree.history.dto.request.MemberActionRequestEvent;

public interface MemberActionLogService {
    void saveMemberActionLog(MemberActionRequestEvent memberActionRequestEvent);
}
