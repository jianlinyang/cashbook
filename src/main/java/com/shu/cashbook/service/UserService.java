package com.shu.cashbook.service;

import com.shu.cashbook.domain.User;

/**
 * @Author: yang
 * @Date: 2019/2/1 22:23
 * @Version 1.0
 */
public interface UserService {
    User findByName(String s);
    User findByEmail(String s);
    void insert(User user);
    User getUser();
    String getUserIcon();
    String getUsername();
    void update(User user);
}
