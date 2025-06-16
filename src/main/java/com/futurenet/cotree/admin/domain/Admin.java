package com.futurenet.cotree.admin.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Admin {
    private Long id;
    private String loginId;
    private String password;
    private String code;
}
