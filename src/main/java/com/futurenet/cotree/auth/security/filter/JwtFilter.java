package com.futurenet.cotree.auth.security.filter;

import com.futurenet.cotree.auth.security.dto.UserAuthDto;
import com.futurenet.cotree.auth.security.dto.UserPrincipal;
import com.futurenet.cotree.auth.util.JwtUtil;
import com.futurenet.cotree.auth.util.ResponseUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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

        if (authorization == null) {
            log.info("Access Token Not Found");
            filterChain.doFilter(request, response);
            return;
        }

        String accessToken = authorization;

        if (jwtUtil.isExpired(accessToken)) {
            ResponseUtil.setResponse(response, "AU001", HttpStatus.UNAUTHORIZED);
            return;
        }

        String category = jwtUtil.getCategory(accessToken);
        if (!"access".equals(category)) {
            ResponseUtil.setResponse(response, "AU002", HttpStatus.UNAUTHORIZED);
            return;
        }

        Long memberId = jwtUtil.getMemberId(accessToken);
        String role = jwtUtil.getRole(accessToken);

        UserAuthDto userAuthDto = new UserAuthDto(memberId, role);
        UserPrincipal userPrincipal = new UserPrincipal(userAuthDto);

        Authentication authToken = new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);
        filterChain.doFilter(request, response);
    }
}