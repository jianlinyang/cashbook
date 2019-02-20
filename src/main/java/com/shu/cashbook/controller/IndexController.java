package com.shu.cashbook.controller;

import com.shu.cashbook.common.BaseResult;
import com.shu.cashbook.common.utils.MainUtils;
import com.shu.cashbook.domain.User;
import com.shu.cashbook.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * @Author: yang
 * @Date: 2019/2/4 19:03
 * @Version 1.0
 */
@Api(tags = "用户注册Controller")
@RestController
public class IndexController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private UserService userService;

    @ApiOperation("用户注册方法")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名"),
            @ApiImplicitParam(name = "userEmail", value = "邮箱"),
            @ApiImplicitParam(name = "userIcon", value = "头像"),
            @ApiImplicitParam(name = "password", value = "密码")})
    @PostMapping("/register")
    @Transactional
    public BaseResult regist(User user) {
        User byEmail = userService.findByEmail(user.getUserEmail());
        if (null != byEmail) {
            return BaseResult.failed(403, "用户名已存在");
        } else {
            user.setId(MainUtils.getUuid());
            user.setUserLevel("0");
            user.setPassword(MainUtils.getBCryptStr(user.getPassword()));
            user.setAuthority("ROLE_USER");
            userService.insert(user);
            logger.info(user.getUsername()+"注册成功");
            return BaseResult.success("注册成功");
        }
    }
}
