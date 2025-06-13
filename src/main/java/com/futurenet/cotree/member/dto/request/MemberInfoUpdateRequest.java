package com.futurenet.cotree.member.dto.request;

import com.futurenet.cotree.member.dto.MemberUpdateInfoDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
public class MemberInfoUpdateRequest {
    private MultipartFile profileImage;
    private String nickname;
    private String ageRange;
    private String gender;

    public MemberUpdateInfoDto toDto(String profileImagePath, Long memberId) {
        return MemberUpdateInfoDto.builder()
                .id(memberId)
                .profileImage(profileImagePath)
                .nickname(nickname)
                .ageRange(ageRange)
                .gender(gender)
                .build();
    }
}
