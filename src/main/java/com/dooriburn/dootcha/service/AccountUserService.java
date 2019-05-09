package com.dooriburn.dootcha.service;

import com.dooriburn.dootcha.mapper.AccountMapper;
import com.dooriburn.dootcha.entity.AccountUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AccountUserService implements UserDetailsService {
    final AccountMapper accountMapper;
    public AccountUserService(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    public AccountUser getUser(String id) {

        return accountMapper.selectAccountUserById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        AccountUser accountUserDTO = accountMapper.selectAccountUserById(id);
        accountUserDTO.setAuthorities(accountUserDTO.getAuthorities());
        if (accountUserDTO == null)
            throw new UsernameNotFoundException(id);
        return accountUserDTO;
    }
}
