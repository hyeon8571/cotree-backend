package com.futurenet.cotree.auth.controller;

import com.futurenet.cotree.auth.service.JwtService;
import com.futurenet.cotree.global.dto.response.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;

    @PostMapping("/refresh-token")
    public ResponseEntity<?> ReissueAccessToken(HttpServletRequest request, HttpServletResponse response) {
        jwtService.reissueAccessToken(request, response);
        return ResponseEntity.ok(new ApiResponse<>("AU100", null));
    }
}
