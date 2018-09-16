package com.mj.auth.service;


import com.mj.auth.model.MenuVo;
import com.mj.auth.model.Result;
import com.mj.auth.service.impl.PermissionServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Created by Mr.Yangxiufeng on 2017/12/29.
 * Time:12:37
 * ProjectName:Mirco-Service-Skeleton
 */
@FeignClient(name = "mss-upms",fallback = PermissionServiceImpl.class)
public interface PermissionService {
    @GetMapping("permission/getRolePermission/{roleId}")
    Result<List<MenuVo>> getRolePermission(@PathVariable("roleId") Integer roleId);
}
