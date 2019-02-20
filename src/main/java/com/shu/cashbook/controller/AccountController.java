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
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: yang
 * @Date: 2019/2/1 22:09
 * @Version 1.0
 */
@Api(tags = "记账Controller")
@RestController
@RequestMapping("home/account")
public class AccountController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private AccountItemService accountItemService;

    /**
     * 获取当前登录用户
     *
     * @return
     */
    private String getUsername() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUsername();
    }

    @PostMapping("addCash")
    @ApiOperation("新增记录")
    @Transactional
    @ApiImplicitParams({
            @ApiImplicitParam(name = "changeMoney", value = "操作金额"),
            @ApiImplicitParam(name = "itemType", value = "收支类型"),
            @ApiImplicitParam(name = "note", value = "备注")})
    public BaseResult addCash(AccountItem accountItem) {
        accountItem.setId(MainUtils.getUuid());
        accountItem.setCreatorId(this.getUsername());
        if (null == accountItem.getCreateTime()) {
            accountItem.setCreateTime(new Date());
        }
        accountItemService.insert(accountItem);
        logger.info(this.getUsername() + "新增记录成功");
        return BaseResult.success("新增记录成功");
    }


    @GetMapping("select/{pageNum}/{pageSize}")
    @Transactional(readOnly = true)
    @ApiOperation("分页查询全部记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码数"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数")})
    public BaseResult selectAll(@PathVariable int pageNum, @PathVariable int pageSize) {
        PageInfo<AccountItem> pageInfo = accountItemService.page(pageNum, pageSize, this.getUsername());
        Map<String,Object> map=new LinkedHashMap<>();
        map.put("totalPage",pageInfo.getPages());   //总页数
        map.put("total",pageInfo.getTotal());       //总数据数
        map.put("pageNum",pageInfo.getPageNum());   //当前页数
        map.put("pageSize",pageInfo.getPageSize()); //每页记录数
        map.put("dataList",pageInfo.getList());     //当前页数据结果集
        String message = "totalPage:总页数 ;total:总数据数  ;pageNum:当前页数 ;pageSize:每页记录数 ;dataList:当前页数据结果集";
        return BaseResult.success(map,message);
    }
}
