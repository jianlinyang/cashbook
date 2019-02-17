package com.shu.cashbook.service.Impl;

import com.shu.cashbook.service.RedisService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @version 1.0
 * @author: yang
 * @date: 2019/2/17 16:39
 */
@Service
public class RedisServiceImpl implements RedisService {
    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public void put(String key, Object value, long seconds) {
        redisTemplate.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
    }

    @Override
    public void get(String key) {
        redisTemplate.opsForValue().get(key);
    }
}
