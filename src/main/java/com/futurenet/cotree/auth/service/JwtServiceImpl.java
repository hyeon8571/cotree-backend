package com.futurenet.cotree.auth.service;

import com.futurenet.cotree.auth.constant.JwtConstants;
import com.futurenet.cotree.auth.dto.request.RefreshTokenSaveRequest;
import com.futurenet.cotree.auth.service.exception.AuthErrorCode;
import com.futurenet.cotree.auth.service.exception.AuthException;
import com.futurenet.cotree.auth.repository.RefreshTokenRepository;
import com.futurenet.cotree.auth.util.JwtUtil;
import com.futurenet.cotree.auth.util.RequestUtil;
import com.futurenet.cotree.auth.util.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    private final JwtUtil jwtUtil;
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    @Transactional
    public void reissueAccessToken(HttpServletRequest request, HttpServletResponse response) {
        String refreshToken = RequestUtil.getRefreshToken(request);

        if (refreshToken == null || jwtUtil.isExpired(refreshToken)) {
            throw new AuthException(AuthErrorCode.REFRESH_ERROR);
        }

        Long memberId = jwtUtil.getMemberId(refreshToken);
        String savedRefreshToken = refreshTokenRepository.getRefreshTokenByMemberId(memberId);

        if (savedRefreshToken == null || !savedRefreshToken.equals(refreshToken)) {
            throw new AuthException(AuthErrorCode.REFRESH_ERROR);
        }

        String role = jwtUtil.getRole(refreshToken);

        String newAccess = jwtUtil.createJwt("access", memberId, role, JwtConstants.ACCESS_EXPIRED);
        String newRefresh = jwtUtil.createJwt("refresh", memberId, role, JwtConstants.REFRESH_EXPIRED);

        refreshTokenRepository.saveRefreshToken(new RefreshTokenSaveRequest(newRefresh, memberId));

        response.addCookie(ResponseUtil.createCookie("Authorization", newAccess, JwtConstants.ACCESS_COOKIE_EXPIRED));
        response.addCookie(ResponseUtil.createCookie("refresh", newRefresh, JwtConstants.REFRESH_COOKIE_EXPIRED));
    }


}
