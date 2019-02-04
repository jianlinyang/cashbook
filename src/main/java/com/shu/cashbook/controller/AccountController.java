package com.shu.cashbook.controller;

import com.shu.cashbook.common.BaseResult;
import com.shu.cashbook.domain.User;
import com.shu.cashbook.mapper.UserMapper;
import com.shu.cashbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("t")
    public BaseResult test() {
        this.testSelect();
        return BaseResult.ok("测试user权限");
    }

    public void testSelect() {
        List<User> users=userMapper.selectAll();
        for (User user : users) {
            System.out.println(user.getUserName());
        }
    }

}
