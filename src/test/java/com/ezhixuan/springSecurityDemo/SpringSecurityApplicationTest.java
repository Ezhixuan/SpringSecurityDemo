package com.ezhixuan.springSecurityDemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

@SpringBootTest
public class SpringSecurityApplicationTest{

    @Resource
    private PasswordEncoder passwordEncoder;

    @Test
    void test(){
        String encode = passwordEncoder.encode("1234");
        // $2a$10$ukg69WWLQ4KwJGmePwgU6edIJG/qXDShraMp9ZBCTm8M0VcrBpc26
        System.out.println(encode);
        boolean matches = passwordEncoder.matches("1234", "$2a$10$ukg69WWLQ4KwJGmePwgU6edIJG/qXDShraMp9ZBCTm8M0VcrBpc26");
        System.out.println(matches);
    }

}
