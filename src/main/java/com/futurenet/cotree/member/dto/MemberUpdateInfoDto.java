package com.futurenet.cotree.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class MemberUpdateInfoDto {
    private Long id;
    private String profileImage;
    private String nickname;
    private String ageRange;
    private String gender;
}
