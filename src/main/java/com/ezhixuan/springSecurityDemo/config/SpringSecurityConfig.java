package com.ezhixuan.springSecurityDemo.config;

import com.ezhixuan.springSecurityDemo.filter.JWTAuthenticationTokenFilter;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * @program: SpringSecurityDemo
 * @description:
 * @author: Mr.Xuan
 * @create: 2023-09-25 23:01
 */
@Configuration
@Slf4j
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig {

  @Resource private JWTAuthenticationTokenFilter jwtAuthenticationTokenFilter;
  @Resource private AccessDeniedHandler accessDeniedHandler;
  @Resource private AuthenticationEntryPoint authenticationEntryPoint;
  @Resource private AuthenticationSuccessHandler authenticationSuccessHandler;
  @Resource private AuthenticationFailureHandler authenticationFailureHandler;
  @Resource private LogoutSuccessHandler logoutSuccessHandler;

  /**
   * 密码加密
   *
   * @return
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /**
   * 过滤器链配置
   *
   * @param http
   * @return
   * @throws Exception
   */
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http // 关闭csrf
        .csrf()
        .disable()
        // 不通过Session获取SecurityContext
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests()
        // 对于登录接口 允许匿名访问
        .antMatchers("/user/login")
        .anonymous()
        // 除上面外的所有请求全部需要鉴权认证
        .anyRequest()
        .authenticated();
    // 添加token校验过滤器到过滤器链中
    http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    // 添加自定义异常处理器
    http.exceptionHandling()
        .accessDeniedHandler(accessDeniedHandler)
        .authenticationEntryPoint(authenticationEntryPoint);
    // 添加允许跨域
    http.cors();
    // 添加自定义处理器
    http.formLogin().successHandler(authenticationSuccessHandler).failureHandler(authenticationFailureHandler);
    http.logout().logoutSuccessHandler(logoutSuccessHandler);
    return http.build();
  }

  /**
   * 获取AuthenticationManager（认证管理器），登录时认证使用
   *
   * @param authenticationConfiguration
   * @return
   * @throws Exception
   */
  @Bean
  public AuthenticationManager authenticationManager(
      AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }
}
