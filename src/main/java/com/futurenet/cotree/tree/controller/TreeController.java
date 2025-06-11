package com.futurenet.cotree.tree.controller;

import com.futurenet.cotree.auth.security.dto.UserPrincipal;
import com.futurenet.cotree.global.dto.response.ApiResponse;
import com.futurenet.cotree.tree.dto.request.GiveWaterRequest;
import com.futurenet.cotree.tree.dto.response.GiveWaterResponse;
import com.futurenet.cotree.tree.dto.response.MyTreeResponse;
import com.futurenet.cotree.tree.service.TreeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tree")
public class TreeController {
    private final TreeService treeService;

    @GetMapping
    public ResponseEntity<?> getMyTree(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        Long memberId = userPrincipal.getId();
        int exp = treeService.getMyTree(memberId);
        return ResponseEntity.ok(new ApiResponse<>("TR100", new MyTreeResponse(exp)));
    }

    @PostMapping
    public ResponseEntity<?> giveWater(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody GiveWaterRequest giveWaterRequest) {
        Long memberId = userPrincipal.getId();
        GiveWaterResponse giveWaterResponse = treeService.giveWater(memberId, giveWaterRequest);
        return ResponseEntity.ok(new ApiResponse<>("TR100", giveWaterResponse));
    }
}
