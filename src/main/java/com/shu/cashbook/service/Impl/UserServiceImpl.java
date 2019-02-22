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
public class UserServiceImpl extends BaseServiceImpl<User, UserMapper> implements UserService {
    @Resource
    private UserMapper userMapper;

    //根据security容器中用户名获取User对象
    @Override
    public User getUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return this.findByName(userDetails.getUsername());
    }

    //获取security容器中用户名
    @Override
    public String getUsername() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUsername();
    }

    //此方法能添加缓存
    @Override
    @Cacheable(value = "month", key = "#s")
    public User findByName(String s) {
        User user = new User();
        user.setUsername(s);
        return userMapper.selectOne(user);
    }

    //以下方法为了更新缓存,覆盖抽象层方法
    @Override
    @CacheEvict(value = "month", key = "#user.username")
    public void update(User user) {
        userMapper.updateByPrimaryKey(user);
    }

    @Override
    @CacheEvict(value = "month", key = "#user.username")
    public void insert(User user) {
        userMapper.insert(user);
    }
}
