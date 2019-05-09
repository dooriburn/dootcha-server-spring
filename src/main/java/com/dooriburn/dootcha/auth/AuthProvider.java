package com.dooriburn.dootcha.auth;

import com.dooriburn.dootcha.entity.AccountUser;
import com.dooriburn.dootcha.service.AccountUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthProvider implements AuthenticationProvider {

    @Autowired
    private AccountUserService accountUserService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("id : " + authentication.getName());
        log.info("pwd : " + authentication.getPrincipal().toString());
        String userId = authentication.getName();
        String userPwd = authentication.getCredentials().toString();

        AccountUser accountUserDTO = (AccountUser) accountUserService.loadUserByUsername(userId);

        if (!matchPwd(userPwd, accountUserDTO.getPassword())) {
            throw new BadCredentialsException(userId);
        }

        if (!accountUserDTO.isEnabled()) {
            throw new BadCredentialsException(userId);
        }

        return new UsernamePasswordAuthenticationToken(userId, userPwd, accountUserDTO.getAuthorities());
    }

    private boolean matchPwd(String inputPwd, String originPwd) {
        return inputPwd.equals(originPwd);
    }

    @Override
    public boolean supports(Class authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
