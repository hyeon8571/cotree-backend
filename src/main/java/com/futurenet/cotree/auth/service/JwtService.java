package com.futurenet.cotree.auth.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface JwtService {
    void reissueAccessToken(HttpServletRequest request, HttpServletResponse response);
}
