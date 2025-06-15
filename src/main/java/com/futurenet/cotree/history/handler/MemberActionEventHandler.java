package com.futurenet.cotree.history.handler;

import com.futurenet.cotree.history.dto.request.MemberActionRequestEvent;
import com.futurenet.cotree.history.service.MemberActionLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class MemberActionEventHandler {

    private final MemberActionLogService memberActionLogService;

    @Async
    @TransactionalEventListener
    public void handleMemberActionLoggedEvent(MemberActionRequestEvent memberActionRequestEvent) {
        memberActionLogService.saveMemberActionLog(memberActionRequestEvent);
    }
}
