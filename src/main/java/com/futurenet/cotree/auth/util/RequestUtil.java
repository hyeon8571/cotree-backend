package com.futurenet.cotree.auth.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

public class RequestUtil {

    public static String getRefreshToken(HttpServletRequest request) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("refresh".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public static String getAccessToken(HttpServletRequest request) {
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


    public static String getAdminId(HttpServletRequest request) {
        String adminId = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("admin".equals(cookie.getName())) {
                    adminId = cookie.getValue();
                    break;
                }
            }
        }
        return adminId;
    }
}
