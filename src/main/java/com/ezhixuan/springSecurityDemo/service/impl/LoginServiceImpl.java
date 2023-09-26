package com.ezhixuan.springSecurityDemo.service.impl;

import com.ezhixuan.springSecurityDemo.domain.ResponseResult;
import com.ezhixuan.springSecurityDemo.domain.entitiy.LoginUser;
import com.ezhixuan.springSecurityDemo.domain.entitiy.User;
import com.ezhixuan.springSecurityDemo.service.LoginService;
import com.ezhixuan.springSecurityDemo.utils.JwtUtil;
import com.ezhixuan.springSecurityDemo.utils.RedisCache;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @program: SpringSecurityDemo
 * @description: 用户登录
 * @author: Mr.Xuan
 * @create: 2023-09-26 08:11
 */
@Service
public class LoginServiceImpl implements LoginService {

  @Resource private AuthenticationManager authenticationManager;

  @Resource private RedisCache redisCache;

  /**
   * 用户登录
   *
   * @return
   */
  @Override
  public ResponseResult login(User user) {

    UsernamePasswordAuthenticationToken token =
        new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
    Authentication authenticate = authenticationManager.authenticate(token);
    if (Objects.isNull(authenticate)) {
      throw new RuntimeException("登录失败");
    }
    LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
    String userId = loginUser.getUser().getId().toString();
    String jwt = JwtUtil.createJWT(userId);
    redisCache.setCacheObject("login:" + userId, loginUser);
    Map<String, String> map = new HashMap<>();
    map.put("token", jwt);
    return new ResponseResult(200, "登录成功", map);
  }
}
