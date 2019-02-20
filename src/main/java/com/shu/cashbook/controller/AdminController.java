package com.shu.cashbook.controller;

import com.shu.cashbook.common.BaseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: yang
 * @Date: 2019/2/1 22:09
 * @Version 1.0
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/t")
    public BaseResult test() {
        return BaseResult.success("测试admin权限");
    }
}
