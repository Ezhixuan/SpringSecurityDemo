package com.ezhixuan.springSecurityDemo.service.impl;


import com.ezhixuan.springSecurityDemo.domain.entitiy.User;
import com.ezhixuan.springSecurityDemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@Slf4j
class UserServiceImplTest {

    @Resource private UserService userService;

    @Test
    void test() {
        List<User> list = userService.list();
        log.info("list = {}",list);
    }

}