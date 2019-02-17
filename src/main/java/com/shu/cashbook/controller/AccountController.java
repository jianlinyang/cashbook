package com.shu.cashbook.controller;

import com.shu.cashbook.common.BaseResult;
import com.shu.cashbook.domain.User;
import com.shu.cashbook.mapper.UserMapper;
import com.shu.cashbook.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: yang
 * @Date: 2019/2/1 22:09
 * @Version 1.0
 */
@RestController
@RequestMapping("account")
public class AccountController {
    @Resource
    private UserService userService;

    @Resource
    private UserMapper userMapper;

    @ApiOperation("user方法测试")
    @GetMapping("t")
    public BaseResult test() {
        List<User> users = userMapper.selectAll();
        return BaseResult.ok(users);
    }
}
