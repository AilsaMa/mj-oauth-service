package com.mj.auth.service.impl;

import com.mj.auth.model.MenuVo;
import com.mj.auth.model.Result;
import com.mj.auth.service.PermissionService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PermissionServiceImpl implements PermissionService {
    @Override
    public Result<List<MenuVo>> getRolePermission(Integer roleId) {
        return Result.failure(100,"调用getRolePermission失败");
    }
}
