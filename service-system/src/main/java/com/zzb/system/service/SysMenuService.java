package com.zzb.system.service;

import com.zzb.model.system.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzb.model.vo.AssginMenuVo;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author zzb
 * @since 2023-05-08
 */
public interface SysMenuService extends IService<SysMenu> {

    List<SysMenu> findNodes();

    void removeMenuById(Long id);

    void updateStatus(String id, Integer status);

    List<SysMenu> findMenuByRoleId(String roleId);

    void doAssign(AssginMenuVo assginMenuVo);
}
