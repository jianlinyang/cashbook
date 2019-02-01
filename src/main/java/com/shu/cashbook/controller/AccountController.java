package com.shu.cashbook.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: yang
 * @Date: 2019/2/1 22:09
 * @Version 1.0
 */
@RestController
@RequestMapping("account")
public class AccountController {
    @GetMapping("t")
    public String test(){
        return "account";
    }
}
