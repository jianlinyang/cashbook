package com.shu.cashbook.service.Impl;

import com.shu.cashbook.domain.BaseDomain;
import com.shu.cashbook.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

/**
 * @version 1.0
 * @author: yang
 * @date: 2019/2/22 19:24
 */
@Service
public abstract class BaseServiceImpl<T extends BaseDomain, D extends MyMapper<T>> implements BaseService<T> {
    @Autowired
    private D mapper;

    @Override
    public List<T> select(T t) {
        return mapper.select(t);
    }

    @Override
    public T selectOne(T t) {
        return mapper.selectOne(t);
    }

    @Override
    public T selectByPrimaryKey(T t) {
        return mapper.selectByPrimaryKey(t.getId());
    }

    @Override
    public void insert(T t) {
        mapper.insert(t);
    }

    @Override
    public void delete(T t) {
        mapper.delete(t);
    }

    @Override
    public void update(T t) {
        mapper.updateByPrimaryKey(t);
    }
}
