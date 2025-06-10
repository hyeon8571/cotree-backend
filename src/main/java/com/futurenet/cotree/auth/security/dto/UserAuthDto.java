package com.futurenet.cotree.auth.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserAuthDto {
    private String username;
    private Long id;
    private String role;

    public UserAuthDto(Long id, String role) {
        this.id = id;
        this.role = role;
    }
}
