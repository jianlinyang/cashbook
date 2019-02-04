package com.shu.cashbook.security;

import com.shu.cashbook.domain.User;
import com.shu.cashbook.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: yang
 * @Date: 2019/2/2 14:30
 * @Version 1.0
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private MyAuthenctiationFailureHandler myAuthenctiationFailureHandler;

    @Autowired
    private MyAuthenctiationSuccessHandler myAuthenctiationSuccessHandler;

    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.formLogin()                    //  定义当需要用户登录时候，转到的登录页面。
                .loginPage("/login.html")           // 设置登录页面
                .loginProcessingUrl("/login")  // 自定义的登录接口
                .successHandler(myAuthenctiationSuccessHandler) // 自定义登录成功处理
                .failureHandler(myAuthenctiationFailureHandler)// 自定义登录失败处理

                .and()
                .authorizeRequests()        // 定义哪些URL需要被保护、哪些不需要被保护
                .antMatchers("/login.html").permitAll()     // 设置所有人都可以访问登录页面
                .antMatchers("/regist").permitAll()     // 设置所有人都可以访问登录页面
                .antMatchers("/account/**").hasRole("USER") //设置权限页面对应角色
                .antMatchers("/**").hasRole("ADMIN")

                .and()
                .rememberMe()     //记住我

                .and()
                .logout().logoutSuccessUrl("/login.html")   //注销

                .and()
                .csrf().disable();         // 关闭csrf防护

    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //设置UserDetailsService以及密码规则
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
