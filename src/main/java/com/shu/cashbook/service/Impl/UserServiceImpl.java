package com.shu.cashbook.service.Impl;

import com.shu.cashbook.domain.User;
import com.shu.cashbook.mapper.UserMapper;
import com.shu.cashbook.service.UserService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @Author: yang
 * @Date: 2019/2/1 22:25
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User getUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return this.findByName(userDetails.getUsername());
    }

    public String getUserIcon(){
        return this.getUser().getUserIcon();
    }

    @Override
    @CacheEvict(value = "month",key = "#user.username")
    public void update(User user) {
        userMapper.updateByPrimaryKey(user);
    }

    @Override
    public String getUsername() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUsername();
    }

    @Override
    public User findByEmail(String s) {
        User user = new User();
        user.setUserEmail(s);
        return userMapper.selectOne(user);
    }

    @Override
    @Cacheable(value = "month",key = "#s")
    public User findByName(String s) {
        User user = new User();
        user.setUsername(s);
        return userMapper.selectOne(user);
    }

    @Override
    @CacheEvict(value = "month",key = "#user.username")
    public void insert(User user) {
        userMapper.insert(user);
    }
}
