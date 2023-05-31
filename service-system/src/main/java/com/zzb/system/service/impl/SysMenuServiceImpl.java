package com.zzb.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzb.model.system.SysMenu;
import com.zzb.model.system.SysRoleMenu;
import com.zzb.model.vo.AssginMenuVo;
import com.zzb.system.exception.ZBException;
import com.zzb.system.mapper.SysMenuMapper;
import com.zzb.system.mapper.SysRoleMenuMapper;
import com.zzb.system.service.SysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zzb.system.utils.MenuHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author zzb
 * @since 2023-05-08
 */

@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;
    @Override
    public List<SysMenu> findNodes() {
        List<SysMenu> menuList = baseMapper.selectList(null);
        //所有菜单数据转换要求数据格式
        return MenuHelper.buildTree(menuList);
    }

    @Override
    public void removeMenuById(Long id) {
        QueryWrapper<SysMenu> wrapper=new QueryWrapper<>();
        wrapper.eq("parent_id",id);
        if(baseMapper.selectCount(wrapper)>0){
            throw new ZBException(201,"请先删除子菜单");
        }
        baseMapper.deleteById(id);
    }

    @Override
    public void updateStatus(String id, Integer status) {
        QueryWrapper<SysMenu> wrapper=new QueryWrapper<>();
        wrapper.eq("id",id);
        List<SysMenu> menuList = baseMapper.selectList(wrapper);
        //List<SysMenu> buildTree = MenuHelper.buildTree(menuList);
        for (SysMenu menu:menuList) {
            menu.setStatus(status);
            baseMapper.updateById(menu);
        }

    }

    @Override
    public List<SysMenu> findMenuByRoleId(String roleId) {

        //获取所有菜单status=1
        QueryWrapper<SysMenu> wrapperMenu=new QueryWrapper<>();
        wrapperMenu.eq("status",1);
        List<SysMenu> menuList = baseMapper.selectList(wrapperMenu);

        //根据角色ID查询 角色分配过的菜单列表
        QueryWrapper<SysRoleMenu> wrapperRole=new QueryWrapper<>();
        wrapperRole.eq("role_id",roleId);
        List<SysRoleMenu> roleMenuList = sysRoleMenuMapper.selectList(wrapperRole);

        //从第二步查询列表中，获取角色分配所有菜单id
        List<String> roleMenuIds=new ArrayList<>();
        for (SysRoleMenu sysRoleMenu:roleMenuList) {
            String menuId = sysRoleMenu.getMenuId();
            roleMenuIds.add(menuId);
        }
        //数据处理，如果已经分配 isSelect=true 否则false
        //拿着分配菜单id和所有菜单对比，有相同的，让isSelect值true
        for (SysMenu sysMenu:menuList) {
            if(roleMenuIds.contains(sysMenu.getId())){
                sysMenu.setSelect(true);
            }else{
                sysMenu.setSelect(false);
            }
        }
        return MenuHelper.buildTree(menuList);
    }

    //给角色分配菜单权限
    @Override
    public void doAssign(AssginMenuVo assginMenuVo) {
        //根据角色ID删除菜单权限
        QueryWrapper<SysRoleMenu> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id",assginMenuVo.getRoleId());
        sysRoleMenuMapper.delete(wrapper);
        //遍历菜单id列表，挨个添加   给角色添加菜单权限
        List<String> menuIdList = assginMenuVo.getMenuIdList();
        for (String menuId:menuIdList) {
            SysRoleMenu sysRoleMenu=new SysRoleMenu();
            sysRoleMenu.setMenuId(menuId);
            sysRoleMenu.setRoleId(assginMenuVo.getRoleId());
            sysRoleMenuMapper.insert(sysRoleMenu);
        }

    }
}
