package com.zzb.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzb.common.result.Result;
import com.zzb.model.system.SysUser;
import com.zzb.model.vo.SysUserQueryVo;
import com.zzb.system.exception.GlobalExceptionHandler;
import com.zzb.system.exception.ZBException;
import com.zzb.system.mapper.SysUserMapper;
import com.zzb.system.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author zzb
 * @since 2023-05-06
 */
@Api(tags = "用户管理接口")
@RestController
@RequestMapping("/admin/system/sysUser")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;
    @ApiOperation("用户列表")
    @GetMapping("/{page}/{limit}")
    public Result userList(@PathVariable Long page, @PathVariable Long limit, SysUserQueryVo sysUserQueryVo){
        Page<SysUser> pageParam=new Page<>(page,limit);
        //调用service方法
        IPage<SysUser> pageModel=sysUserService.selectPage(pageParam,sysUserQueryVo);
        return Result.ok(pageModel);
    }
    @ApiOperation("添加用户")
    @PostMapping("save")
    public Result save(@RequestBody SysUser sysUser){
        //把输入密码进行MD5加密
        String password = DigestUtils.md5DigestAsHex(sysUser.getPassword().getBytes());
        sysUser.setPassword(password);
        QueryWrapper<SysUser> wrapper=new QueryWrapper<>();
        wrapper.eq("username",sysUser.getUsername());
        //wrapper.in("is_deleted", Arrays.asList(1,0));
        wrapper.isNull("is_deleted");
        int count = sysUserService.existsByUsername(sysUser.getUsername());
        System.out.println(count);
        if(count>0){
            return Result.fail().code(201).message("该账号已被注册");
        }else {
            boolean isSuccess = sysUserService.save(sysUser);
            if(isSuccess)
                return Result.ok();
            else
                return Result.fail();
        }
    }
    @ApiOperation("删除用户")
    @DeleteMapping("remove/{id}")
    public Result del(@PathVariable Long id){
        boolean isSuccess = sysUserService.removeById(id);
        if (isSuccess)
            return Result.ok();
        else
            return Result.fail();
    }
    @ApiOperation("根据ID查询")
    @GetMapping("getUser/{id}")
    public Result getUser(@PathVariable Long id){
        SysUser sysUser = sysUserService.getById(id);
        return Result.ok(sysUser);
    }
    @ApiOperation("修改用户")
    @PostMapping("update")
    public Result update(@RequestBody SysUser sysUser){
        boolean isSuccess = sysUserService.updateById(sysUser);
        if(isSuccess)
            return Result.ok();
        else
            return Result.fail();
    }
    @ApiOperation("更改用户状态")
    @GetMapping("update/{id}/{status}")
    public Result updateStatus(@PathVariable String id,@PathVariable Integer status){
        sysUserService.updateStatus(id,status);
        return Result.ok();
    }


}

