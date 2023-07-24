package com.zzb.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzb.model.system.SysUser;
import com.zzb.model.vo.RouterVo;
import com.zzb.model.vo.SysUserQueryVo;
import com.zzb.system.mapper.SysMenuMapper;
import com.zzb.system.mapper.SysUserMapper;
import com.zzb.system.service.SysMenuService;
import com.zzb.system.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private SysMenuService sysMenuService;
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

    @Override
    public int existsByUsername(String username) {
        return baseMapper.existsByUsername(username);
    }

    @Override
    public SysUser getUserInfoByUserName(String username) {
        QueryWrapper<SysUser> wrapper=new QueryWrapper<>();
        wrapper.eq("username",username);
        return baseMapper.selectOne(wrapper);
    }

    @Override
    public Map<String,Object> getUserInfo(String username) {
        SysUser sysUser = this.getUserInfoByUserName(username);
        List<RouterVo> routerVoList =sysMenuService.getUserMenuList(sysUser.getId());
        List<String> buttonList =sysMenuService.getUserButtonList(sysUser.getId());

        Map<String,Object> result=new HashMap<>();
        result.put("name",username);
        result.put("roles","[admin]");
        result.put("routers",routerVoList);
        result.put("buttons",buttonList);
        result.put("introduction","I am a super administrator");
        result.put("avatar","https://thirdwx.qlogo.cn/mmopen/vi_32/kj8UwADicsWCCsVnB00RJt7ZnUYppia7aQMJibpmSVhhISfIr2Ew7eRd2n2e6fxqVqicXxiaNt6d671oNu2KRPkvYtA/132 ");
        return result;
    }

}
