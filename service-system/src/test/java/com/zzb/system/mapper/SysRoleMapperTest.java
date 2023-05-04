package com.zzb.system.mapper;

import com.zzb.model.system.SysRole;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class SysRoleMapperTest {
    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Test
    public void queryByColumn(){
        QueryWrapper<SysRole> wrapper=new QueryWrapper<>();
        wrapper.like("role_name","管理员");
        System.out.println(sysRoleMapper.selectList(wrapper));
    }
    @Test
    public void delList() {
        int rows = sysRoleMapper.deleteBatchIds(Arrays.asList(2, 8));
        System.out.println(rows);
    }

    @Test
    public void del() {
        int rows = sysRoleMapper.deleteById(1649759099115589634L);
        System.out.println(rows);
    }

    @Test
    public void update() {
        SysRole role = sysRoleMapper.selectById(1);
        role.setDescription((role.getDescription()) + "张智滨");
        int rows = sysRoleMapper.updateById(role);
        System.out.println(rows);
    }

    @Test
    public void add() {
        SysRole role = new SysRole();
        role.setRoleName("测试角色");
        role.setRoleCode("testManager");
        role.setDescription("测试角色");
        int rows = sysRoleMapper.insert(role);
        System.out.println(rows);
    }

    @Test
    public void findAll() {
        List<SysRole> list = sysRoleMapper.selectList(null);
        for (SysRole s :
                list) {
            System.out.println(s);
        }
    }

}
