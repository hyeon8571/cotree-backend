package com.futurenet.cotree.history.service;

import com.futurenet.cotree.history.dto.request.MemberActionRequestEvent;
import com.futurenet.cotree.history.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberActionLogServiceImpl implements MemberActionLogService {

    private final LogRepository logRepository;

    @Override
    @Transactional
    public void saveMemberActionLog(MemberActionRequestEvent memberActionRequestEvent) {
        logRepository.saveMemberActionLog(memberActionRequestEvent);
    }
}
