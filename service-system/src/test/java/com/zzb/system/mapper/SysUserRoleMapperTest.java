package com.zzb.system.mapper;

import com.atguigu.model.system.SysUserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SysUserRoleMapperTest {
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Test
    public void findAll(){
        List<SysUserRole> sysUserRoles = sysUserRoleMapper.selectList(null);
        for (SysUserRole s:
             sysUserRoles) {
            System.out.println(s);
        }
    }
}
