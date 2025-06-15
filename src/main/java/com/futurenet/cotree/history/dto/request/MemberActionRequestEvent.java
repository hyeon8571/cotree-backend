package com.futurenet.cotree.history.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberActionRequestEvent {
    private Long memberId;
    private String gender;
    private int age;
    private Long itemId;
    private String keyword;

    //아이템 상세 조회
    public MemberActionRequestEvent(Long memberId, String gender, int age, Long itemId) {
        this.memberId = memberId;
        this.gender = gender;
        this.age = age;
        this.itemId = itemId;
    }

    //아이템 검색
    public MemberActionRequestEvent(Long memberId, String gender, int age, String keyword) {
        this.memberId = memberId;
        this.gender = gender;
        this.age = age;
        this.keyword = keyword;
    }
}
