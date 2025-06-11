package com.futurenet.cotree.tree.controller;

import com.futurenet.cotree.auth.security.dto.UserPrincipal;
import com.futurenet.cotree.global.dto.response.ApiResponse;
import com.futurenet.cotree.tree.dto.response.MyTreeResponse;
import com.futurenet.cotree.tree.service.TreeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tree")
public class TreeController {
    private final TreeService treeService;

    @GetMapping
    public ResponseEntity<?> getMyTree(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        Long memberId = userPrincipal.getId();
        Integer exp = treeService.getMyTree(memberId);
        return ResponseEntity.ok(new ApiResponse<>("TR100", new MyTreeResponse(exp)));
    }
}
