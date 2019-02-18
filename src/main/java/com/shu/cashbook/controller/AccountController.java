package com.shu.cashbook.controller;

import com.github.pagehelper.PageInfo;
import com.shu.cashbook.common.BaseResult;
import com.shu.cashbook.common.utils.MainUtils;
import com.shu.cashbook.domain.AccountItem;
import com.shu.cashbook.service.AccountItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author: yang
 * @Date: 2019/2/1 22:09
 * @Version 1.0
 */
@Api(tags = "记账Controller")
@RestController
@RequestMapping("account")
public class AccountController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private AccountItemService accountItemService;

    /**
     * 获取当前登录用户
     * @return
     */
    private String getUsername() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUsername();
    }

    @PostMapping("insert")
    @ApiOperation("新增记录")
    @Transactional
    @ApiImplicitParams({
                    @ApiImplicitParam(name = "changeMoney", value = "支出金额"),
                    @ApiImplicitParam(name = "itemType", value = "消费事件")})
    public BaseResult insert(AccountItem accountItem) {
        accountItem.setId(MainUtils.getUuid());
        accountItem.setCreatorId(this.getUsername());
        accountItem.setCreateTime(new Date());
        accountItemService.insert(accountItem);
        logger.info(this.getUsername() + "新增记录成功");
        return BaseResult.success("新增记录成功");
    }


    @GetMapping("select/all")
    @Transactional(readOnly = true)
    @ApiOperation("分页查询全部记录")
    @ApiImplicitParams({
                    @ApiImplicitParam(name = "pageNum", value = "页码数"),
                    @ApiImplicitParam(name = "pageSize", value = "每页记录数")})
    public BaseResult selectAll(int pageNum, int pageSize) {
        PageInfo<AccountItem> page = accountItemService.page(pageNum, pageSize, this.getUsername());
        List<AccountItem> list = page.getList();

        return BaseResult.success(page);
    }
}
