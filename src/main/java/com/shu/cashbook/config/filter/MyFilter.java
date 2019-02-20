package com.shu.cashbook.config.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

/**
 * @version 1.0
 * @author: yang
 * @date: 2019/2/20 13:25
 */
public class MyFilter implements Filter {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.info("测试 filter");
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
