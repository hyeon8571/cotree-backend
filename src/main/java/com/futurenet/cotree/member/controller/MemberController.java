package com.futurenet.cotree.member.controller;

import com.futurenet.cotree.auth.security.dto.UserPrincipal;
import com.futurenet.cotree.global.dto.response.ApiResponse;
import com.futurenet.cotree.member.dto.response.MyPageResponse;
import com.futurenet.cotree.member.service.MemberFindService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberFindService memberFindService;

    @GetMapping
    public ResponseEntity<?> getMemberInfo(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        MyPageResponse response = memberFindService.getMyPageInfo(userPrincipal.getId());
        return ResponseEntity.ok(new ApiResponse<>("ME100", response));
    }
}
