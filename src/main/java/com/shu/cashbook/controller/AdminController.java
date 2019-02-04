package com.shu.cashbook.controller;

import com.shu.cashbook.common.BaseResult;
import com.shu.cashbook.domain.User;
import com.shu.cashbook.mapper.UserMapper;
import com.shu.cashbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: yang
 * @Date: 2019/2/1 22:09
 * @Version 1.0
 */
@RestController
@RequestMapping("admin")
public class AdminController {

    @GetMapping("t")
    public BaseResult test() {
        return BaseResult.ok("测试admin权限");
    }
}
