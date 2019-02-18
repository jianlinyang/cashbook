package com.shu.cashbook.security;

import com.shu.cashbook.common.BaseResult;
import com.shu.cashbook.common.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: yang
 * @Date: 2019/2/3 16:12
 * @Version 1.0
 * 登录失败处理
 */
@Component
public class MyAuthenctiationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        logger.info("登录失败");
        BaseResult baseResult= BaseResult.failed(401,"用户名密码错误");
        try {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JsonUtils.obj2json(baseResult));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
