package com.dooriburn.dootcha.auth;

import com.dooriburn.dootcha.entity.AccountUser;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;


import java.util.List;

@Getter
@Setter
public class MyAuthentication extends UsernamePasswordAuthenticationToken {

    private static final long serialVersionUID = 1L;

    AccountUser accountUserDTO;

    public MyAuthentication(String id, String pwd, List<GrantedAuthority> authorityList, AccountUser accountUserDTO) {
        super(id, pwd);
        this.accountUserDTO = accountUserDTO;
    }
}
