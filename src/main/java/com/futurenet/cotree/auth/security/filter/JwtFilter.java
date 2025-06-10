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
import org.springframework.security.authentication.AuthenticationServiceException;
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
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("Authorization")) {
                authorization = cookie.getValue();
            }
        }

        if (authorization == null) {
            log.info("Access Token Not Found");
            filterChain.doFilter(request, response);
            return;
        }

        String accessToken = authorization;

        try {
            if (jwtUtil.isExpired(accessToken)) {
                request.setAttribute("errorCode", "AU001");
                throw new AuthenticationServiceException("AU001");
            }

            String category = jwtUtil.getCategory(accessToken);

            if (!category.equals("access")) {
                request.setAttribute("errorCode", "AU002");
                throw new AuthenticationServiceException("AU002");
            }

            Long memberId = jwtUtil.getMemberId(accessToken);
            String role = jwtUtil.getRole(accessToken);

            UserAuthDto userAuthDto = new UserAuthDto(memberId, role);
            UserPrincipal userPrincipal = new UserPrincipal(userAuthDto);

            Authentication authToken = new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authToken);
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            request.setAttribute("errorCode", "AU002");
            throw new AuthenticationServiceException("AU002");
        }

    }
}
