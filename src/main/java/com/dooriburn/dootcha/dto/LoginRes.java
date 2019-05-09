package com.dooriburn.dootcha.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginRes {
    private int nsns_id;
    private String token;

}
