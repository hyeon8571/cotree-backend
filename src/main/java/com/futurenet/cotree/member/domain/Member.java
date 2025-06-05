package com.futurenet.cotree.member.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Member {
    private Long id;
    private String email;
    private String provider;
    private String nickname;
    private String profileImage;
    private String gender;
    private String ageRange;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
