package com.zzb.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzb.model.system.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzb.model.vo.SysUserQueryVo;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author zzb
 * @since 2023-05-06
 */
public interface SysUserService extends IService<SysUser> {

    //用户列表
    IPage<SysUser> selectPage(Page<SysUser> pageParam, SysUserQueryVo sysUserQueryVo);

    void updateStatus(String id, Integer status);
}
