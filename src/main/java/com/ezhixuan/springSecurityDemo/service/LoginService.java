package com.ezhixuan.springSecurityDemo.service;

import com.ezhixuan.springSecurityDemo.domain.ResponseResult;
import com.ezhixuan.springSecurityDemo.domain.entitiy.User;

public interface LoginService {
    /**
     * 用户登录
     * @return
     */
    ResponseResult login(User user);

}
