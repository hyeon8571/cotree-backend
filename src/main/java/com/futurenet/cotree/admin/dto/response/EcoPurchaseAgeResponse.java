package com.futurenet.cotree.admin.dto.response;

import com.futurenet.cotree.member.constant.MemberAge;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EcoPurchaseAgeResponse {
    private MemberAge memberAge;
    private int age;
}
