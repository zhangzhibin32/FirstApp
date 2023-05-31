package com.zzb.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzb.model.system.SysRole;
import com.zzb.model.system.SysUser;
import com.zzb.model.vo.SysRoleQueryVo;
import com.zzb.model.vo.SysUserQueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author zzb
 * @since 2023-05-06
 */
@Repository

public interface SysUserMapper extends BaseMapper<SysUser> {
    public IPage<SysUser> selectPage(Page<SysUser> pageParam, @Param("vo") SysUserQueryVo sysUserQueryVo);


}
