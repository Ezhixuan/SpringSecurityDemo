package com.ezhixuan.springSecurityDemo.handler;

import cn.hutool.json.JSONUtil;
import com.ezhixuan.springSecurityDemo.domain.ResponseResult;
import com.ezhixuan.springSecurityDemo.utils.WebUtils;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

/**
 * @program: SpringSecurityDemo
 * @description: 自定义异常处理机制
 * @author: Mr.Xuan
 * @create: 2023-09-26 15:46
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
  @Override
  public void handle(
      HttpServletRequest request,
      HttpServletResponse response,
      AccessDeniedException accessDeniedException)
      throws IOException, ServletException {
    ResponseResult result = new ResponseResult(HttpStatus.FORBIDDEN.value(), "权限不足");
    String jsonStr = JSONUtil.toJsonStr(result);
    WebUtils.renderString(response, jsonStr);
  }
}
