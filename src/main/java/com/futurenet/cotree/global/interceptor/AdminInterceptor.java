package com.futurenet.cotree.global.interceptor;

import com.futurenet.cotree.admin.domain.Admin;
import com.futurenet.cotree.admin.repository.AdminRepository;
import com.futurenet.cotree.auth.util.RequestUtil;
import com.futurenet.cotree.auth.util.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class AdminInterceptor implements HandlerInterceptor {

    private final AdminRepository adminRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String adminToken = request.getHeader("admin-token");

        String adminId = RequestUtil.getAdminId(request);

        if (adminId == null) {
            ResponseUtil.setResponse(response, "AD000", HttpStatus.UNAUTHORIZED);
            return false;
        }

        if (adminToken == null || adminToken.isEmpty()) {
            ResponseUtil.setResponse(response, "AD000", HttpStatus.UNAUTHORIZED);
            return false;
        }

        Admin admin = adminRepository.getAdminById(Long.valueOf(adminId));

        if (!BCrypt.checkpw(admin.getCode(), adminToken)) {
            ResponseUtil.setResponse(response, "AD000", HttpStatus.UNAUTHORIZED);
            return false;
        }

        return true;
    }
}
