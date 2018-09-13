package com.springboot.basicauth.service;

import com.springboot.basicauth.pojo.CustomUserDetails;
import com.springboot.basicauth.pojo.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        /*模拟数据库操作*/
        User user = new User();
        user.setUsername("10086");
        user.setPassword("123456");
        return new CustomUserDetails(user);
    }

}