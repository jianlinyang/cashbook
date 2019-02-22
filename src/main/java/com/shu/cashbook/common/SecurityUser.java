package com.shu.cashbook.common;

import com.shu.cashbook.domain.User;
import com.shu.cashbook.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @version 1.0
 * @author: yang
 * @date: 2019/2/22 10:35
 */

/**
 * 获取当前登录用户信息
 */
@Component
public class SecurityUser {
    @Resource
    private UserService userService;

    //返回用户对象
    public User getUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.findByName(userDetails.getUsername());
    }

    //返回用户名
    public String getUsername(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUsername();
    }

    //返回用户头像
    public String getUserIcon(){
        return this.getUser().getUserIcon();
    }
}
