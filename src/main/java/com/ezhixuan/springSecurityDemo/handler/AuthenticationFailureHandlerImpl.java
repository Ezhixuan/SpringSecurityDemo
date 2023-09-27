package com.ezhixuan.springSecurityDemo.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: SpringSecurityDemo
 * @description: 登录失败处理器
 * @author: Mr.Xuan
 * @create: 2023-09-27 15:37
 */
@Component
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler
{
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.getWriter().write("登录失败");

    }
}
