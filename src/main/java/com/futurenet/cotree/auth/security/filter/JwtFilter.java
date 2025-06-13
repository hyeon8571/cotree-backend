package com.futurenet.cotree.auth.security.filter;

import com.futurenet.cotree.auth.security.dto.UserAuthDto;
import com.futurenet.cotree.auth.security.dto.UserPrincipal;
import com.futurenet.cotree.auth.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String accessToken = extractTokenFromCookie(request);

        if (accessToken != null && !jwtUtil.isExpired(accessToken)) {
            if ("access".equals(jwtUtil.getCategory(accessToken))) {
                Long memberId = jwtUtil.getMemberId(accessToken);
                String role = jwtUtil.getRole(accessToken);

                UserPrincipal userPrincipal = new UserPrincipal(new UserAuthDto(memberId, role));
                Authentication auth = new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        filterChain.doFilter(request, response);
    }

    private String extractTokenFromCookie(HttpServletRequest request) {
        String authorization = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("Authorization".equals(cookie.getName())) {
                    authorization = cookie.getValue();
                    break;
                }
            }
        }

        return authorization;
    }
}