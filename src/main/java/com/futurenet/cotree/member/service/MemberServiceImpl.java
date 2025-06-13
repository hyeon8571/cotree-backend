package com.futurenet.cotree.member.service;

import com.futurenet.cotree.global.service.AmazonS3Service;
import com.futurenet.cotree.member.dto.request.MemberInfoUpdateRequest;
import com.futurenet.cotree.member.repository.MemberRepository;
import com.futurenet.cotree.member.service.exception.MemberErrorCode;
import com.futurenet.cotree.member.service.exception.MemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final AmazonS3Service amazonS3Service;

    @Override
    @Transactional
    public void updateMemberInfo(MemberInfoUpdateRequest request, Long memberId) {
        String profileImage = null;

        try {
            profileImage = amazonS3Service.uploadImage(request.getProfileImage());
        } catch (IOException e) {
            throw new MemberException(MemberErrorCode.MEMBER_UPDATE_FAIL);
        }

        int result = memberRepository.updateMemberInfo(request.toDto(profileImage, memberId));

        if (result == 0) {
            throw new MemberException(MemberErrorCode.MEMBER_UPDATE_FAIL);
        }
    }
}
