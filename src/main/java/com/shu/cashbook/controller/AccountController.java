package com.shu.cashbook.controller;

import com.github.pagehelper.PageInfo;
import com.shu.cashbook.common.BaseResult;
import com.shu.cashbook.common.SecurityUser;
import com.shu.cashbook.common.utils.JsonUtils;
import com.shu.cashbook.common.utils.MainUtils;
import com.shu.cashbook.domain.AccountItem;
import com.shu.cashbook.service.AccountItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Author: yang
 * @Date: 2019/2/1 22:09
 * @Version 1.0
 */
@Api(tags = "记账Controller")
@RestController
@RequestMapping("home")
public class AccountController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private AccountItemService accountItemService;
    @Resource
    private SecurityUser securityUser;

    @PostMapping("account")
    @ApiOperation("新增记录")
    @Transactional
    @ApiImplicitParams({
            @ApiImplicitParam(name = "changeMoney", value = "操作金额", dataType = "double"),
            @ApiImplicitParam(name = "itemType", value = "收支类型"),
            @ApiImplicitParam(name = "createTime", value = "时间", dataType = "date"),
            @ApiImplicitParam(name = "note", value = "备注")})
    public BaseResult addCash(AccountItem accountItem) {
        if (accountItem.getChangeMoney() == null) {
            return BaseResult.failed(403, "金额不能为空,请输入金额");
        }
        accountItem.setId(MainUtils.getUuid());
        accountItem.setCreatorId(securityUser.getUsername());
        if (null == accountItem.getCreateTime()) {
            accountItem.setCreateTime(new Date());
        }
        accountItemService.insert(accountItem);
        logger.info("{}新增记录成功", securityUser.getUsername());
        return BaseResult.success("新增记录成功");
    }


    //@GetMapping("select/{pageNum}/{pageSize}")
    @GetMapping("account")
    @Transactional(readOnly = true)
    @ApiOperation("分页查询全部记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码数", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", dataType = "int")})
    public BaseResult selectAll(int pageNum, int pageSize) {
        PageInfo<AccountItem> pageInfo = accountItemService.page(pageNum, pageSize, securityUser.getUsername());
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("totalPage", pageInfo.getPages());   //总页数
        map.put("total", pageInfo.getTotal());       //总数据数
        map.put("pageNum", pageInfo.getPageNum());   //当前页数
        map.put("pageSize", pageInfo.getPageSize()); //每页记录数
        map.put("dataList", pageInfo.getList());     //当前页数据结果集
        String message = "totalPage:总页数 ;total:总数据数  ;pageNum:当前页数 ;pageSize:每页记录数 ;dataList:当前页数据结果集";
        logger.info("{}查询成功", securityUser.getUsername());
        return BaseResult.success(map, message);
    }

    @PutMapping("account")
    @Transactional
    @ApiOperation("更新记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID"),
            @ApiImplicitParam(name = "changeMoney", value = "操作金额", dataType = "double"),
            @ApiImplicitParam(name = "itemType", value = "收支类型"),
            @ApiImplicitParam(name = "createTime", value = "时间", dataType = "date"),
            @ApiImplicitParam(name = "note", value = "备注")})
    public BaseResult update(AccountItem accountItem) {
        if (accountItemService.selectOne(accountItem.getId()) == null || null == accountItem.getChangeMoney()) {
            return BaseResult.failed(403, "请输入更改数据");
        } else {
            if (accountItem.getCreateTime() == null) {
                accountItem.setCreateTime(new Date());
            }
            accountItem.setCreatorId(securityUser.getUsername());
            accountItemService.update(accountItem);
            logger.info("{}更新记录{}成功", securityUser.getUsername(),accountItem.getId());
            return BaseResult.success("数据更新成功");
        }
    }

    @DeleteMapping("account")
    @Transactional
    @ApiOperation("删除记录")
    @ApiImplicitParam(name="ids",value = "需删id的Json(List)")
    public BaseResult delete(String ids) {
        if (StringUtils.isBlank(ids)) {
            return BaseResult.failed(403, "请选择要删除的内容");
        }
        AccountItem accountItem = new AccountItem();
        try {
            List<String> strings = JsonUtils.json2list(ids, String.class);
            for (String s : strings) {
                accountItem.setId(s);
                accountItemService.delete(accountItem);
            }
            logger.info("{}删除记录{}成功", securityUser.getUsername(),ids);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return BaseResult.success("删除成功");
    }
}
