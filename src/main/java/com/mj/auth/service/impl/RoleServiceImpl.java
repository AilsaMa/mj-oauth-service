package com.mj.auth.service.impl;

import com.mj.auth.model.Result;
import com.mj.auth.model.RoleVo;
import com.mj.auth.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Mr.Yangxiufeng
 * Date: 2018-06-13
 * Time: 11:06
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Override
    public Result<List<RoleVo>> getRoleByUserId(Integer userId) {
        return Result.failure(100,"调用getRoleByUserId失败");
    }
}
