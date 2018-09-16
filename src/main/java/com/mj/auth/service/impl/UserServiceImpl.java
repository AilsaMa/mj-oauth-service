package com.mj.auth.service.impl;

import com.mj.auth.model.Result;
import com.mj.auth.model.UserVo;
import com.mj.auth.service.UserService;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    @Override
    public Result<UserVo> findByUsername(String username) {
        return Result.failure(100,"调用findByUsername接口失败");
    }
}
