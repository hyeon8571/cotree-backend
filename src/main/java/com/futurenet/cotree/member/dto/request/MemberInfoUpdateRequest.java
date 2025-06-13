package com.futurenet.cotree.member.dto.request;

import com.futurenet.cotree.member.dto.MemberUpdateInfoDto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberInfoUpdateRequest {
    @NotNull
    private MultipartFile profileImage;
    @NotNull
    private String nickname;
    @NotNull
    private String ageRange;
    @NotNull
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
