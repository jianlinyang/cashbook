package com.shu.cashbook.controller;

import com.shu.cashbook.common.BaseResult;
import com.shu.cashbook.domain.User;
import com.shu.cashbook.service.UserService;
import com.shu.cashbook.common.utils.MainUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: yang
 * @Date: 2019/2/4 19:03
 * @Version 1.0
 */
@RestController
public class IndexController {
    @Autowired
    private UserService userService;

    @PostMapping("regist")
    @Transactional
    public BaseResult regist(@RequestParam String email,
                             @RequestParam String name,
                             @RequestParam(required = false) String level,
                             @RequestParam(required = false) String icon,
                             @RequestParam String password) {
        User byEmail = userService.findByEmail(email);
        if (null != byEmail) {
            return BaseResult.failed(403,"用户名已存在");
        } else {
            User user = new User();
            user.setId(MainUtils.getUuid());
            user.setUserEmail(email);
            user.setUsername(name);
            user.setUserLevel(level);
            user.setUserIcon(icon);
            user.setPassword(MainUtils.getBCryptStr(password));
            user.setAuthority("ROLE_USER");
            userService.insert(user);
            return BaseResult.success("注册成功");
        }
    }
}
