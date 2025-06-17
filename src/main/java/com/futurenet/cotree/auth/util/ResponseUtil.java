package com.futurenet.cotree.auth.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.futurenet.cotree.global.dto.response.ApiResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;

import java.io.IOException;

public class ResponseUtil {

    public static void setResponse(HttpServletResponse response, String code, HttpStatus status) throws IOException {
        response.setStatus(status.value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        ApiResponse<?> apiResponse = new ApiResponse<>(code, null);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(apiResponse);
        response.getWriter().write(jsonResponse);
    }

    public static Cookie createCookie(String key, String value, int expired) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(expired);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);

        return cookie;
    }
}
