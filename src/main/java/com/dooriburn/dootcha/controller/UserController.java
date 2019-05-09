package com.dooriburn.dootcha.controller;

import com.dooriburn.dootcha.dto.AccountUserForm;
import com.dooriburn.dootcha.dto.LoginRes;
import com.dooriburn.dootcha.entity.AccountUser;
import com.dooriburn.dootcha.entity.User;
import com.dooriburn.dootcha.response.DefaultRes;
import com.dooriburn.dootcha.response.ResponseMessage;
import com.dooriburn.dootcha.response.StatusCode;
import com.dooriburn.dootcha.service.AccountUserService;
import com.dooriburn.dootcha.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;
import static com.dooriburn.dootcha.response.DefaultRes.FAIL_DEFAULT_RES;

@Slf4j
@RestController
public class UserController {
    final AccountUserService accountUserService;

    final AuthenticationManager authenticationManager;


    public UserController(AccountUserService accountUserService, AuthenticationManager authenticationManager) {
        this.accountUserService = accountUserService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login/account")
    public ResponseEntity joinAccount(@RequestBody AccountUserForm accountUserForm) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(accountUserForm.getId(), accountUserForm.getPassword());
            AccountUser accountUser = accountUserService.getUser(accountUserForm.getId());
//            String token = "Bearer " +
            )
        }
        catch (AuthenticationException e) {
            log.error(e.getMessage());
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.INVALID_USER_DATA), HttpStatus.OK);
        }
        catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//
//    public LoginRes createJwtToken(User user) {
//        String token = "Bearer " +
//    }

}
