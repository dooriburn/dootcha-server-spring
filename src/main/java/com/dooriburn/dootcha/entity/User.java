package com.dooriburn.dootcha.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int user_id;
    private LocalDateTime created_at;
    private boolean account_expired;
    private String authority;
    private boolean sns_at;
    private boolean enable;


}
