package com.ezhixuan.springSecurityDemo.controller;

import com.ezhixuan.springSecurityDemo.domain.ResponseResult;
import com.ezhixuan.springSecurityDemo.domain.entitiy.User;
import com.ezhixuan.springSecurityDemo.service.LoginService;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * @program: SpringSecurityDemo
 * @description: 登录控制器
 * @author: Mr.Xuan
 * @create: 2023-09-26 08:09
 **/
@RestController
@RequestMapping("/user")
public class LoginController {

    @Resource
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user){
        return loginService.login(user);
    }
}
