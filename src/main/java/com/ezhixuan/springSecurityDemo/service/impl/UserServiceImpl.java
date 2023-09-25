package com.ezhixuan.springSecurityDemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ezhixuan.springSecurityDemo.domain.entitiy.User;
import com.ezhixuan.springSecurityDemo.mapper.UserMapper;
import com.ezhixuan.springSecurityDemo.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @program: SpringSecurityDemo
 * @description:
 * @author: Mr.Xuan
 * @create: 2023-09-25 21:00
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
