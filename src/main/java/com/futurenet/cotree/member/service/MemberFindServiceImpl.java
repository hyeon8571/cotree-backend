package com.futurenet.cotree.member.service;

import com.futurenet.cotree.member.domain.Member;
import com.futurenet.cotree.member.dto.response.MyPageResponse;
import com.futurenet.cotree.member.repository.MemberRepository;
import com.futurenet.cotree.member.service.exception.MemberErrorCode;
import com.futurenet.cotree.member.service.exception.MemberException;
import com.futurenet.cotree.order.constant.OrderStatus;
import com.futurenet.cotree.order.dto.MemberOrderStatusDto;
import com.futurenet.cotree.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberFindServiceImpl implements MemberFindService {

    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;

    @Override
    @Transactional
    public Member getMemberByEmail(String email) {
        return memberRepository.getMemberByEmail(email);
    }

    @Override
    @Transactional
    public MyPageResponse getMyPageInfo(Long id) {

        MyPageResponse myPageResponse =  memberRepository.getMyPageInfo(id);

        if (myPageResponse == null) {
            throw new MemberException(MemberErrorCode.MEMBER_NOT_FOUND);
        }

        List<MemberOrderStatusDto> orderStatusResponses = orderRepository.getOrderStatus(id);

        int orderStatusPaidCount = 0;
        int orderStatusPendingCount = 0;

        for (MemberOrderStatusDto response : orderStatusResponses) {
            if (OrderStatus.SUCCESS.getStatus().equals(response.getStatus())) {
                orderStatusPaidCount = response.getCount();
            } else if (OrderStatus.WAITING.getStatus().equals(response.getStatus())) {
                orderStatusPendingCount = response.getCount();
            }
        }

        myPageResponse.setOrderStatusPaidCount(orderStatusPaidCount);
        myPageResponse.setOrderStatusPendingCount(orderStatusPendingCount);

        return myPageResponse;
    }
}
