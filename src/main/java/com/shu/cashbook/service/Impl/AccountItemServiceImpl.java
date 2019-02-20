package com.shu.cashbook.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shu.cashbook.domain.AccountItem;
import com.shu.cashbook.mapper.AccountItemMapper;
import com.shu.cashbook.service.AccountItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @version 1.0
 * @author: yang
 * @date: 2019/2/17 21:16
 */
@Service
public class AccountItemServiceImpl implements AccountItemService {
    @Resource
    private AccountItemMapper accountItemMapper;

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @param s
     * @return
     */
    @Override
    public PageInfo<AccountItem> page(int pageNum, int pageSize, String s ) {
        AccountItem accountItem = new AccountItem();
        accountItem.setCreatorId(s);
        //分页
        PageHelper.startPage(pageNum, pageSize, "create_time desc");
        List<AccountItem> select = accountItemMapper.select(accountItem);
        //包装分页结果
        PageInfo<AccountItem> pageInfo = new PageInfo<>(select);
        return pageInfo;
    }


    @Override
    public void insert(AccountItem accountItem) {
        accountItemMapper.insert(accountItem);
    }

    @Override
    public void update(AccountItem old, AccountItem now) {
        accountItemMapper.updateByExampleSelective(old, now);
    }

    @Override
    public void delete(AccountItem accountItem) {
        accountItemMapper.delete(accountItem);
    }
}
