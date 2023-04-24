package com.zzb.system.service.impl;

import com.atguigu.model.system.SysRole;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzb.system.mapper.SysRoleMapper;
import com.zzb.system.service.SysRoleService;
import org.springframework.stereotype.Service;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
}
