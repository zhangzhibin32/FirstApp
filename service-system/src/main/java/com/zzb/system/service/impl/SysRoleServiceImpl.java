package com.zzb.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzb.model.system.SysRole;
import com.zzb.model.system.SysUserRole;
import com.zzb.model.vo.AssginRoleVo;
import com.zzb.model.vo.SysRoleQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzb.system.mapper.SysRoleMapper;
import com.zzb.system.mapper.SysUserRoleMapper;
import com.zzb.system.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public IPage<SysRole> selectPage(Page<SysRole> pageParam, SysRoleQueryVo sysRoleQueryVo) {
        IPage<SysRole> pageModel = baseMapper.selectPage(pageParam, sysRoleQueryVo);
        return pageModel;
    }

    @Override
    public Map<String, Object> getRole(String userId) {
        //获取所有角色
        List<SysRole> roles = baseMapper.selectList(null);
        //根据用户ID查询  已经分配角色
        QueryWrapper<SysUserRole> wrapper=new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        List<SysUserRole> sysRoleList = sysUserRoleMapper.selectList(wrapper);
        List<String> userRoleIds=new ArrayList<>();
        for (SysUserRole userRole:sysRoleList) {
            String roleId = userRole.getRoleId();
            userRoleIds.add(roleId);
        }
        Map<String,Object> map=new HashMap<>();
        map.put("allRoles",roles);
        map.put("userRoleIds",userRoleIds);
        return map;
    }

    @Override
    public void doAssign(AssginRoleVo assginRoleVo) {
        //根据用户ID删除之前分配的角色
        QueryWrapper<SysUserRole> wrapper=new QueryWrapper<>();
        wrapper.eq("user_id",assginRoleVo.getUserId());
        sysUserRoleMapper.delete(wrapper);
        //获取所有角色id，添加角色用户关系表
        List<String> roleIdList = assginRoleVo.getRoleIdList();
        for (String roleId:roleIdList) {
            SysUserRole sysUserRole=new SysUserRole();
            sysUserRole.setRoleId(roleId);
            sysUserRole.setUserId(assginRoleVo.getUserId());
            sysUserRoleMapper.insert(sysUserRole);
        }

    }
}
