package com.zzb.system.service;

import com.zzb.model.system.SysRole;
import com.zzb.model.vo.AssginRoleVo;
import com.zzb.model.vo.SysRoleQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

public interface SysRoleService  extends IService<SysRole> {
    IPage<SysRole> selectPage(Page<SysRole> pageParam, SysRoleQueryVo sysRoleQueryVo);

    Map<String, Object> getRole(String userId);

    void doAssign(AssginRoleVo assginRoleVo);
}
