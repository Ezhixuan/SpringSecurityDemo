package com.ezhixuan.springSecurityDemo.handler;

import cn.hutool.json.JSONUtil;
import com.ezhixuan.springSecurityDemo.domain.ResponseResult;
import com.ezhixuan.springSecurityDemo.utils.WebUtils;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * @program: SpringSecurityDemo
 * @description: 自定义异常处理机制
 * @author: Mr.Xuan
 * @create: 2023-09-26 15:45
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

  @Override
  public void commence(
      HttpServletRequest request,
      HttpServletResponse response,
      AuthenticationException authException)
      throws IOException, ServletException {
    ResponseResult result = new ResponseResult(HttpStatus.UNAUTHORIZED.value(), "认证失败请重新登录");
    String jsonStr = JSONUtil.toJsonStr(result);
    // String jsonStr = JSON.toJSONString(request); fastjson由于数组中携带有Request或者Response对象，无法正常序列化
    WebUtils.renderString(response, jsonStr);
  }
}
