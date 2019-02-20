package com.shu.cashbook.config;

import com.shu.cashbook.config.filter.MyFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * @version 1.0
 * @author: yang
 * @date: 2019/2/20 13:24
 */
@Configuration
public class MyServerConfig {
    @Bean
    public FilterRegistrationBean myFilter(){
        FilterRegistrationBean registrationBean=new FilterRegistrationBean();
        registrationBean.setFilter(new MyFilter());
        registrationBean.setUrlPatterns(Arrays.asList("/login","/account/*"));
        return registrationBean;
    }
}
