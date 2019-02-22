package com.shu.cashbook.service;

import com.shu.cashbook.domain.User;

/**
 * @Author: yang
 * @Date: 2019/2/1 22:23
 * @Version 1.0
 */
public interface UserService extends BaseService<User> {
    //此方法能添加缓存
    User findByName(String s);

    User getUser();

    String getUsername();
}
