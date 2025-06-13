package com.futurenet.cotree.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class MemberUpdateAgeAndGenderDto {
    private Long id;
    private String ageRange;
    private String gender;
}
