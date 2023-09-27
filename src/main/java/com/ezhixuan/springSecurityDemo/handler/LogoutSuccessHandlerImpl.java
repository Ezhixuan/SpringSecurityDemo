package com.ezhixuan.springSecurityDemo.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: SpringSecurityDemo
 * @description: 退出成功处理器
 * @author: Mr.Xuan
 * @create: 2023-09-27 15:38
 */
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler{
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.getWriter().write("退出成功");

    }
}
