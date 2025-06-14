package com.futurenet.cotree.member.dto.request;

import com.futurenet.cotree.member.dto.MemberUpdateAgeAndGenderDto;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberAgeAndGenderRequest {
    @NotNull(message = "연령대를 입력해주세요.")
    private String ageRange;
    @NotNull(message = "성별을 입력해주세요.")
    private String gender;

    public MemberUpdateAgeAndGenderDto toDto(Long memberId) {
        return MemberUpdateAgeAndGenderDto.builder()
                .id(memberId)
                .ageRange(ageRange)
                .gender(gender)
                .build();
    }
}
