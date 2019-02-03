package com.shu.cashbook.security;

import com.shu.cashbook.domain.User;
import com.shu.cashbook.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yang
 * @Date: 2019/2/2 14:31
 * @Version 1.0
 */
@Component
public class MyUserDetailsService implements UserDetailsService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Resource
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("用户的用户名: {}", username);
        User user = userService.findOne(username);
        if(user==null){
            throw new UsernameNotFoundException("用户名"+username+"不存在");
        }
        String password = passwordEncoder.encode(user.getPassword());
        logger.info("password: {}", password);

        //获取权限
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if (user.getAuthority() != null) {
            authorities.add(new SimpleGrantedAuthority(user.getAuthority()));
        }

        // 将user属性赋给myUserDetails
        return new MyUserDetails(user.getId(),user.getUserName(), password,user.getUserEmail(),user.getUserIcon(),true, authorities);
    }
}
