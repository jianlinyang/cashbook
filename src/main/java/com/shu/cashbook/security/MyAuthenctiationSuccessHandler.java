package com.shu.cashbook.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: yang
 * @Date: 2019/2/3 13:29
 * @Version 1.0
 *登录成功处理
 */
@Component
public class MyAuthenctiationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        logger.info("登录成功");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("success");
    }
}
