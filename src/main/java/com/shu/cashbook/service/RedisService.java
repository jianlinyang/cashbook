package com.shu.cashbook.service;

/**
 * @version 1.0
 * @author: yang
 * @date: 2019/2/17 16:38
 */
public interface RedisService {
    void put(String key, Object value, long seconds);

    void get(String key);
}
