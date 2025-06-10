package com.futurenet.cotree.member.service;

import com.futurenet.cotree.member.domain.Member;
import com.futurenet.cotree.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberFindServiceImpl implements MemberFindService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public Member getMemberByEmail(String email) {
        return memberRepository.getMemberByEmail(email);
    }
}
