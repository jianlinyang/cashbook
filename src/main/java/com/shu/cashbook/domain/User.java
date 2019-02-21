package com.shu.cashbook.domain;

import javax.persistence.*;
import java.io.Serializable;

public class User implements Serializable {
    /**
     * 用户id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 用户邮箱
     */
    @Column(name = "user_email")
    private String userEmail;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 用户等级
     */
    @Column(name = "user_level")
    private String userLevel;

    /**
     * 用户头像地址
     */
    @Column(name = "user_icon")
    private String userIcon;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户权限
     */
    private String authority;

    /**
     * 获取用户id
     *
     * @return id - 用户id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置用户id
     *
     * @param id 用户id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取用户邮箱
     *
     * @return user_email - 用户邮箱
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * 设置用户邮箱
     *
     * @param userEmail 用户邮箱
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    /**
     * 获取用户名称
     *
     * @return username - 用户名称
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名称
     *
     * @param username 用户名称
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取用户等级
     *
     * @return user_level - 用户等级
     */
    public String getUserLevel() {
        return userLevel;
    }

    /**
     * 设置用户等级
     *
     * @param userLevel 用户等级
     */
    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    /**
     * 获取用户头像地址
     *
     * @return user_icon - 用户头像地址
     */
    public String getUserIcon() {
        return userIcon;
    }

    /**
     * 设置用户头像地址
     *
     * @param userIcon 用户头像地址
     */
    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    /**
     * 获取用户密码
     *
     * @return password - 用户密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置用户密码
     *
     * @param password 用户密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取用户权限
     *
     * @return authority - 用户权限
     */
    public String getAuthority() {
        return authority;
    }

    /**
     * 设置用户权限
     *
     * @param authority 用户权限
     */
    public void setAuthority(String authority) {
        this.authority = authority;
    }
}