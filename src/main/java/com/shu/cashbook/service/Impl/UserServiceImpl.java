package com.shu.cashbook.service.Impl;

import com.shu.cashbook.domain.User;
import com.shu.cashbook.mapper.UserMapper;
import com.shu.cashbook.service.UserService;
import org.springframework.cache.annotation.Cacheable;
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
    public User findByEmail(String s) {
        User user = new User();
        user.setUserEmail(s);
        return userMapper.selectOne(user);
    }

    @Override
    @Cacheable(value = "month",keyGenerator = "simpleKeyGenerator")
    public User findByName(String s) {
        User user = new User();
        user.setUsername(s);
        return userMapper.selectOne(user);
    }

    @Override
    public void insert(User user) {
        userMapper.insert(user);
    }
}
