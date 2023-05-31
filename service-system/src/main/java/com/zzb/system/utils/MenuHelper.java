package com.zzb.system.utils;

import com.zzb.model.system.SysMenu;

import java.util.ArrayList;
import java.util.List;

public class MenuHelper {
    public static List<SysMenu> buildTree(List<SysMenu> list){
        //最终集合
        List<SysMenu> tree=new ArrayList<>();
        for (SysMenu sysMenu:list) {
            //递归入口 parent_id=0
            if(sysMenu.getParentId().longValue()==0){
                tree.add(findChildren(sysMenu,list));
            }
        }
        return tree;
    }
    //从根节点进行递归查询，查询子节点
    //判断id==parentid是否相同，如果相同就是他的子节点，进行数据封装

    public static SysMenu findChildren(SysMenu sysMenu,List<SysMenu> treeNodes){
        //数据初始化
        sysMenu.setChildren(new ArrayList<SysMenu>());
        //遍历递归查找
        for (SysMenu it: treeNodes) {
            if(it.getParentId()==Long.parseLong(sysMenu.getId())){
                if(sysMenu.getChildren()==null)
                    sysMenu.setChildren(new ArrayList<>());
                sysMenu.getChildren().add(findChildren(it,treeNodes));
            }
        }
        return sysMenu;
    }
}
