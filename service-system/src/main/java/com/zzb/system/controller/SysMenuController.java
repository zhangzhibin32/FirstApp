package com.zzb.system.controller;


import com.zzb.common.result.Result;
import com.zzb.model.system.SysMenu;
import com.zzb.model.vo.AssginMenuVo;
import com.zzb.system.service.SysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author zzb
 * @since 2023-05-08
 */
@Api(tags = "菜单管理")
@RestController
@RequestMapping("/admin/system/sysMenu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @ApiOperation("给角色分配菜单权限")
    @PostMapping("/doAssign")
    public Result doAssign(@RequestBody AssginMenuVo assginMenuVo){
        sysMenuService.doAssign(assginMenuVo);
        return Result.ok();
    }

    //根据角色来分配菜单
    @ApiOperation("根据角色来获取菜单")
    @GetMapping("/toAssign/{roleId}")
    public Result toAssign(@PathVariable String roleId){
        List<SysMenu> list=sysMenuService.findMenuByRoleId(roleId);
        return Result.ok(list);
    }
    //菜单列表 树形
    @ApiOperation("菜单列表")
    @GetMapping("findNodes")
    public Result findNodes(){
        List<SysMenu> list= sysMenuService.findNodes();
        return Result.ok(list);
    }
    @ApiOperation("添加菜单")
    @PostMapping("save")
    public Result save(@RequestBody SysMenu sysMenu){
        sysMenuService.save(sysMenu);
        return Result.ok();
    }

    @ApiOperation("根据ID查询")
    @GetMapping("findNode/{id}")
    public Result findNodeById(@PathVariable Long id){
        SysMenu sysMenu = sysMenuService.getById(id);
        return Result.ok(sysMenu);
    }

    @ApiOperation("修改菜单")
    @PostMapping("update")
    public Result update(@RequestBody SysMenu sysMenu){
        boolean is_success = sysMenuService.updateById(sysMenu);
        if(is_success)
            return Result.ok();
        else
            return Result.fail();
    }
    @ApiOperation("删除菜单")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id){
        sysMenuService.removeMenuById(id);
        return Result.ok();
    }
    @ApiOperation("更改菜单状态")
    @GetMapping("update/{id}/{status}")
    public Result updateStatus(@PathVariable String id,@PathVariable Integer status){
        sysMenuService.updateStatus(id,status);
        return Result.ok();
    }

}

