package com.shu.cashbook.security;

import com.shu.cashbook.common.BaseResult;
import com.shu.cashbook.common.utils.JsonUtils;
import com.shu.cashbook.domain.User;
import com.shu.cashbook.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: yang
 * @Date: 2019/2/3 13:29
 * @Version 1.0
 * 登录成功处理
 */
@Component
public class MyAuthenctiationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.info("{} 登录成功", userDetails.getUsername());

        User user = userService.findByName(userDetails.getUsername());
        user.setPassword(null);
        BaseResult baseResult = BaseResult.success(user);
        baseResult.setMessage("登录成功");
        try {
            String json = JsonUtils.obj2json(baseResult);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
