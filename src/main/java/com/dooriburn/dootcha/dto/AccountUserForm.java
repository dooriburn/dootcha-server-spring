package com.dooriburn.dootcha.dto;

import com.dooriburn.dootcha.entity.AccountUser;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AccountUserForm {
    private String id;
    private String password;

    public AccountUser toAccountUser() {
        return AccountUser.builder()
                .id(id)
                .password(password)
                .authority("ROLE_USER")
                .build();
    }
}
