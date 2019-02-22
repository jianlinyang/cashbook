package com.shu.cashbook.service;

import com.github.pagehelper.PageInfo;
import com.shu.cashbook.domain.AccountItem;

/**
 * @version 1.0
 * @author: yang
 * @date: 2019/2/17 21:12
 */
public interface AccountItemService extends BaseService<AccountItem> {
    PageInfo<AccountItem> page(int pageNum, int pageSize, String s);
}
