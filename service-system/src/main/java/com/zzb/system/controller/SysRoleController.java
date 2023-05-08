package com.zzb.system.controller;

import com.zzb.model.system.SysRole;
import com.zzb.model.vo.AssginRoleVo;
import com.zzb.model.vo.SysRoleQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzb.common.result.Result;
import com.zzb.system.exception.ZBException;
import com.zzb.system.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Api(tags = "角色管理")
@RestController
@RequestMapping("/admin/system/sysRole")
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;
    //查询所有记录
    @ApiOperation("查询所有接口")
    @GetMapping("findAll")
    public Result findAllRole(){
        return Result.ok(sysRoleService.list());
    }
    @ApiOperation("批量删除")
    @DeleteMapping("removeByIds")
    public Result removeByIds(@RequestBody List<Long>ids){
        boolean isSuccess = sysRoleService.removeByIds(ids);
        if(isSuccess)
            return Result.ok();
        else
            return Result.fail();
    }
    @ApiOperation("修改角色")
    @PostMapping("update")
    public Result updateSysRole(@RequestBody SysRole sysRole){
        boolean isSuccess = sysRoleService.updateById(sysRole);
        if(isSuccess)
            return Result.ok();
        else
            return Result.fail();
    }
    @ApiOperation("根据ID查询")
    @PostMapping("findById/{id}")
    public Result findById(@PathVariable Long id){
        SysRole sysRole = sysRoleService.getById(id);
        return Result.ok(sysRole);
    }
    @ApiOperation("添加角色")
    @PostMapping("save")
    public Result saveRole(@RequestBody SysRole sysRole){
        boolean isSuccess = sysRoleService.save(sysRole);
        if(isSuccess)
            return Result.ok();
        else
            return Result.fail();
    }
    @ApiOperation("逻辑删除接口")
    //逻辑删除接口
    @DeleteMapping("remove/{id}")
    public Result removeRole(@PathVariable Long id ){
        boolean isSuccess = sysRoleService.removeById(id);
        if(isSuccess)
            return Result.ok();
        else
            return Result.fail();
    }

    /**
     * param
     * page 当前页
     * limit 每页记录数
     */
    @ApiOperation("条件分页查询")
    @GetMapping("{page}/{limit}")
    public Result findPageQueryRole(@PathVariable Long page, @PathVariable Long limit, SysRoleQueryVo sysRoleQueryVo){
        //创建page
        Page<SysRole> pageParam=new Page<>(page,limit);
        //调用service方法
        IPage<SysRole> pageModel =sysRoleService.selectPage(pageParam,sysRoleQueryVo);

        return Result.ok(pageModel);
    }

    @ApiOperation("获取用户角色数据")
    @GetMapping("toAssign/{userId}")
    public Result toAssign(@PathVariable String userId){
        Map<String, Object> roleMap= sysRoleService.getRole(userId);
        return Result.ok(roleMap);
    }
    @ApiOperation("用户分配角色")
    @PostMapping("doAssign")
    public Result doAssign(@RequestBody AssginRoleVo assginRoleVo){
        sysRoleService.doAssign(assginRoleVo);
        return Result.ok();
    }
}
