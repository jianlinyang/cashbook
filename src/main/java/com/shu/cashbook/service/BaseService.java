package com.shu.cashbook.service;

import com.shu.cashbook.domain.BaseDomain;

import java.util.List;

/**
 * @version 1.0
 * @author: yang
 * @date: 2019/2/22 19:12
 */
public interface BaseService<T extends BaseDomain> {
    List<T> select(T t);

    T selectOne(T t);

    T selectByPrimaryKey(T t);

    void insert(T t);

    void delete(T t);

    void update(T t);
}
