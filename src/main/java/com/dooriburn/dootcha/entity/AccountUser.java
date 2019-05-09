package com.dooriburn.dootcha.entity;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountUser extends User implements UserDetails {

    private int nsns_id;
    private String id;
    private String password;
    private String email;
    private LocalDateTime last_pwd_updated;

    private Collection<? extends GrantedAuthority> authorities;

    public void setPassword(String password) {
        this.password = password;
    }


    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> auth = new ArrayList<>();
        auth.add(new SimpleGrantedAuthority(super.getAuthority()));
        return auth;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return id;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Builder
    public AccountUser(int user_id, String authority, LocalDateTime createdAt, LocalDateTime updatedAt, String id, String password, String email) {
        super(user_id, createdAt, false, authority, false, true);
        this.id = id;
        this.password = password;
        this.email = email;
        this.last_pwd_updated = updatedAt;
    }
}
