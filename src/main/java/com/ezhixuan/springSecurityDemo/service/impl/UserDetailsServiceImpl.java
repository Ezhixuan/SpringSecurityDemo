package com.ezhixuan.springSecurityDemo.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ezhixuan.springSecurityDemo.domain.entitiy.LoginUser;
import com.ezhixuan.springSecurityDemo.domain.entitiy.User;
import com.ezhixuan.springSecurityDemo.mapper.MenuMapper;
import com.ezhixuan.springSecurityDemo.mapper.UserMapper;
import java.util.List;
import java.util.Objects;
import javax.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @program: SpringSecurityDemo
 * @description:
 * @author: Mr.Xuan
 * @create: 2023-09-25 21:15
 **/
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;
    
    @Resource
    private MenuMapper menuMapper;

    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may possibly be case sensitive, or case insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a username that is of a different case than what
     * was actually requested..
     *
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 查询用户信息
        User user = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUserName, username));
        if (Objects.isNull(user)){
            throw new RuntimeException("用户名或密码错误");
        }
        List<String> permissions = menuMapper.selectPermsByUserId(user.getId());
        LoginUser loginUser = new LoginUser(user,permissions);
        return loginUser;
    }
}
