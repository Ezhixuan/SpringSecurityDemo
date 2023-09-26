package com.ezhixuan.springSecurityDemo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: SpringSecurityDemo
 * @description: SpringSecurity控制器
 * @author: Mr.Xuan
 * @create: 2023-09-25 17:56
 **/
@RestController
@RequestMapping("/demo")
public class SecurityController {

    @PreAuthorize("hasAuthority('sys:test:list')")
    @GetMapping("test")
    public String demoTest(){
        return "hello";
    }
}
