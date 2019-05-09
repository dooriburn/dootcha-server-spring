package com.dooriburn.dootcha.config;

import com.dooriburn.dootcha.auth.AuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    @Autowired
    AuthProvider authProvider;

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 허용되어야 할 경로들
        web.ignoring().antMatchers(
                "/resources/static",
                "/resources/templates",
                "/user/login",
                "/user/join");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        // 로그인 설정
        http.authorizeRequests()
                // ROLE_USER, ROLE_ADMIN으로 권한 분리 URL 정의
                .antMatchers("/", "/user/login", "/error**").permitAll()
                .antMatchers("/**").access("ROLE_USER")
                .antMatchers("/**").access("ROLE_ADMIN")
                .antMatchers("/admin/**").access("ROLE_ADMIN")
                .antMatchers("/**").authenticated();

        http.formLogin()
            // 로그인 페이지 및 성공 URL, Handler, 로그인 시 사용되는 ID, PWD 정의
            .loginPage("/user/login")
            .defaultSuccessUrl("/")
//                .failureHandler()
//                .successHandler()
            .usernameParameter("id")
            .passwordParameter("password");
        http.logout()
            // 로그아웃 관련 설정
            .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
            .logoutSuccessUrl("/")
            .invalidateHttpSession(true);

        http.authenticationProvider(authProvider);

    }
}
