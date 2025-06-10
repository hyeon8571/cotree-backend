package com.futurenet.cotree.auth.security.handler;

import com.futurenet.cotree.auth.util.ResponseUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String errorCode = request.getAttribute("errorCode").toString();
        log.error("Authentication Failed: {}", errorCode);
        ResponseUtil.setResponse(response, errorCode, HttpStatus.UNAUTHORIZED);
    }
}
