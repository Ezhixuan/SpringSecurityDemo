package com.ezhixuan.springSecurityDemo.filter;

import com.ezhixuan.springSecurityDemo.domain.entitiy.LoginUser;
import com.ezhixuan.springSecurityDemo.utils.JwtUtil;
import com.ezhixuan.springSecurityDemo.utils.RedisCache;
import io.jsonwebtoken.Claims;
import java.io.IOException;
import java.util.Objects;
import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @program: SpringSecurityDemo
 * @description: 校验JWT是否有效
 * @author: Mr.Xuan
 * @create: 2023-09-26 09:28
 */
@Component
public class JWTAuthenticationTokenFilter extends OncePerRequestFilter {

  @Resource private RedisCache redisCache;
  
  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    // 1. 获取token
    String token = request.getHeader("token");
    if (!StringUtils.hasText(token)) {
      // token 不存在
      filterChain.doFilter(request, response);
      return;
    }
    // token 存在
    // 2. 解析token
    String userId;
    try {
      Claims claims = JwtUtil.parseJWT(token);
      userId = claims.getSubject();
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("token非法");
    }
    // 3. 从redis中获取用户信息
    LoginUser loginUser = redisCache.getCacheObject("login:" + userId);
    if (Objects.isNull(loginUser)){
      throw new RuntimeException("用户未登录");
    }
    // 4. 存入SecurityContextHolder
    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    // 5. 放行
    filterChain.doFilter(request,response);
  }
}
