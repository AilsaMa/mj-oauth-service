package com.mj.auth.model;

import org.springframework.security.core.userdetails.User;

import java.util.Collections;


/**
 * 自定义UserDetails类 携带User实例
 */
public class MyUserDetails extends User {

    private com.mj.auth.model.User user;

    public MyUserDetails(com.mj.auth.model.User user) {
        super(user.getUserName(), user.getPassword(), true, true, true, true, Collections.EMPTY_SET);
        this.user = user;
    }

    public com.mj.auth.model.User getUser() {
        return user;
    }

    public void setUser(com.mj.auth.model.User user) {
        this.user = user;
    }
}