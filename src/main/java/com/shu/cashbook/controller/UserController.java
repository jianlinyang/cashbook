package com.shu.cashbook.controller;

import com.shu.cashbook.common.BaseResult;
import com.shu.cashbook.common.ProjectPath;
import com.shu.cashbook.common.utils.MainUtils;
import com.shu.cashbook.domain.User;
import com.shu.cashbook.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

/**
 * @Author: yang
 * @Date: 2019/2/4 19:03
 * @Version 1.0
 */
@Api(tags = "用户相关Controller")
@RestController
public class UserController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private UserService userService;

    @PostMapping("register")
    @ApiOperation("用户注册方法")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名"),
            @ApiImplicitParam(name = "userEmail", value = "邮箱"),
            @ApiImplicitParam(name = "userIcon", value = "头像"),
            @ApiImplicitParam(name = "password", value = "密码")})
    @Transactional
    public BaseResult regist(User user) {
        if (StringUtils.isBlank(user.getUsername()) ||
                StringUtils.isBlank(user.getUserEmail())) {
            return BaseResult.failed(403, "用户名不能为空");
        } else {
            User byEmail = userService.findByEmail(user.getUserEmail());
            User byName = userService.findByName(user.getUsername());
            if (null != byEmail || null != byName) {
                return BaseResult.failed(403, "用户名已存在");
            } else {
                if (StringUtils.isBlank(user.getPassword())) {
                    return BaseResult.failed(403, "密码不能为空");
                } else {
                    user.setId(MainUtils.getUuid());
                    user.setUserLevel("0");
                    user.setPassword(MainUtils.getBCryptStr(user.getPassword()));
                    user.setAuthority("ROLE_USER");
                    userService.insert(user);
                    logger.info("{}注册成功", user.getUsername());
                    return BaseResult.success("注册成功");
                }
            }
        }
    }

    @PostMapping("upload")
    @ApiOperation("用户上传头像方法")
    @ApiImplicitParam(name = "multipartFile", value = "头像图片文件")
    @Transactional
    public BaseResult upload(MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            return BaseResult.failed(403, "请上传头像");
        }
        String uploadFilename = multipartFile.getOriginalFilename();
        logger.info("图片名{}", uploadFilename);
        File uploadPath = ProjectPath.getFile();
        if (!uploadPath.exists()) uploadPath.mkdirs();
        logger.info("upload url:" + uploadPath.getAbsolutePath());
        // 将文件写入目标
        String fileSuffix = uploadFilename.substring(uploadFilename.lastIndexOf("."));
        String icon = userService.getUser().getId() + fileSuffix;
        File file = new File(uploadPath, icon);
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //更新user数据库信息
        User user = userService.findByName(userService.getUsername());
        user.setUserIcon(icon);
        userService.update(user);
        logger.info("{}上传成功", uploadFilename);
        return BaseResult.success("上传头像成功");
    }

    @GetMapping("icon")
    @ApiOperation("用户获取头像方法")
    @ApiImplicitParam(name = "multipartFile", value = "头像图片文件")
    @Transactional(readOnly = true)
    public BaseResult getIcon() {
        String path = ProjectPath.getFile().getPath() + File.separatorChar + userService.getUserIcon();
        logger.info("用户头像Url:{}", path);
        return BaseResult.success(path, "获取用户头像Url");
    }
}
