package com.mj.auth.service;

import com.mj.auth.model.Result;
import com.mj.auth.model.RoleVo;
import com.mj.auth.service.impl.RoleServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


/**
 * Created by Mr.Yangxiufeng on 2017/12/29.
 * Time:12:30
 * ProjectName:Mirco-Service-Skeleton
 */
@FeignClient(name = "mss-upms",fallback = RoleServiceImpl.class)
public interface RoleService {
    @GetMapping("role/getRoleByUserId/{userId}")
    Result<List<RoleVo>> getRoleByUserId(@PathVariable("userId") Integer userId);
}
