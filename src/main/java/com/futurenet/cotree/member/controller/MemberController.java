package com.futurenet.cotree.member.controller;

import com.futurenet.cotree.auth.security.dto.UserPrincipal;
import com.futurenet.cotree.global.dto.response.ApiResponse;
import com.futurenet.cotree.member.dto.request.MemberAgeAndGenderRequest;
import com.futurenet.cotree.member.dto.request.MemberInfoUpdateRequest;
import com.futurenet.cotree.member.dto.response.MyPageResponse;
import com.futurenet.cotree.member.service.MemberFindService;
import com.futurenet.cotree.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberFindService memberFindService;
    private final MemberService memberService;

    @GetMapping
    public ResponseEntity<?> getMemberInfo(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        MyPageResponse response = memberFindService.getMyPageInfo(userPrincipal.getId());
        return ResponseEntity.ok(new ApiResponse<>("ME100", response));
    }

    @PatchMapping
    public ResponseEntity<?> updateMemberInfo(@Valid @ModelAttribute MemberInfoUpdateRequest request,
                                              @AuthenticationPrincipal UserPrincipal userPrincipal) {
        memberService.updateMemberInfo(request, userPrincipal.getId());
        return ResponseEntity.ok(new ApiResponse<>("ME101", null));
    }

    @PatchMapping("/age-gender")
    public ResponseEntity<?> setAgeRangeAndGender(@Valid @RequestBody MemberAgeAndGenderRequest request,
                                                  @AuthenticationPrincipal UserPrincipal userPrincipal) {
        memberService.updateMemberAgeAndGender(request, userPrincipal.getId());
        return ResponseEntity.ok(new ApiResponse<>("ME102", null));
    }
}
