package com.shu.cashbook.service;

import com.shu.cashbook.domain.AccountItem;

import java.util.List;

/**
 * @version 1.0
 * @author: yang
 * @date: 2019/2/17 21:12
 */
public interface AccountItemService {
    List<AccountItem> page(int pageNum, int pageSize, String s);
    void insert(AccountItem accountItem);
    void update(AccountItem old,AccountItem now);
    void delete(AccountItem accountItem);
}
