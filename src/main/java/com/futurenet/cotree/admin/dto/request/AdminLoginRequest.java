package com.futurenet.cotree.admin.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AdminLoginRequest {
    private String loginId;
    private String password;
}
