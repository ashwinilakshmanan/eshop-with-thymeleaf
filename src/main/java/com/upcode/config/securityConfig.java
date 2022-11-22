package com.upcode.config;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.security.ConditionalOnDefaultWebSecurity;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.util.StringUtils;

import java.util.List;

@Configuration

public class securityConfig {

    static class SecurityFilterChainConfiguration {

        @Bean
        SecurityFilterChain authenticateRequest(HttpSecurity http) throws Exception {
            http.authorizeRequests().
            antMatchers("/health").permitAll()
            .anyRequest().authenticated();
            http.formLogin();
            http.httpBasic();
            return http.build();
        }
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(SecurityProperties properties,
                                                                 ObjectProvider<PasswordEncoder> passwordEncoder) {
        SecurityProperties.User user = new SecurityProperties.User();
        user.setName("user");
        user.setPassword("pass");
        List<String> roles = List.of("Admin");

        return new InMemoryUserDetailsManager(
                User.withUsername(user.getName()).password(user.getPassword())
                        .roles(StringUtils.toStringArray(roles)).build());
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return rawPassword.equals(encodedPassword);
            }
        };
    }



}
