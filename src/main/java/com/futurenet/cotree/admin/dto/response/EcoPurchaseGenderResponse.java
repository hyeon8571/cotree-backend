package com.futurenet.cotree.admin.dto.response;

import com.futurenet.cotree.member.constant.MemberGender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EcoPurchaseGenderResponse {
    String gender;
    int count;
}
