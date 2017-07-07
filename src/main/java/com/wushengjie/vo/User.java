package com.wushengjie.vo;

import java.util.Date;

/**
 * Created by wu on 2017/7/7.
 * 用户
 */
public class User extends BaseVO{

    private String loginName;//登录名

    private String userName;//用户名

    private String password;//密码

    private Boolean expired;//过期

    private Boolean locked;//被锁

    private Boolean enabled;//禁用

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getExpired() {
        return expired;
    }

    public void setExpired(Boolean expired) {
        this.expired = expired;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
