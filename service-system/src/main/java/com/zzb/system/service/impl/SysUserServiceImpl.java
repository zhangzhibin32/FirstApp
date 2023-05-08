package com.zzb.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzb.model.system.SysRole;
import com.zzb.model.system.SysUser;
import com.zzb.model.vo.SysUserQueryVo;
import com.zzb.system.mapper.SysUserMapper;
import com.zzb.system.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author zzb
 * @since 2023-05-06
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Override
    public IPage<SysUser> selectPage(Page<SysUser> pageParam, SysUserQueryVo sysUserQueryVo) {
        IPage<SysUser> pageModel = baseMapper.selectPage(pageParam, sysUserQueryVo);
        return pageModel;
    }

    @Override
    public void updateStatus(String id, Integer status) {
        //根据用户id 查询
        SysUser sysUser = baseMapper.selectById(id);
        //设置修改状态
        sysUser.setStatus(status);
        //调用方法修改
        baseMapper.updateById(sysUser);
    }
}
