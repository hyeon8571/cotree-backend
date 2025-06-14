package com.futurenet.cotree.member.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyPageResponse {
    private String nickname;
    private String ageRange;
    private String gender;
    private String profileImage;
    private int greenPoint;
    private int orderStatusPendingCount;
    private int orderStatusPaidCount;
}
