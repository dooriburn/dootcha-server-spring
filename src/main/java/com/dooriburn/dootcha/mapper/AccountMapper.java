package com.dooriburn.dootcha.mapper;

import com.dooriburn.dootcha.entity.AccountUser;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface AccountMapper {
    AccountUser selectAccountUserById(String id);
}
