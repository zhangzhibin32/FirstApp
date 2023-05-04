package com.zzb.system.mapper;

import com.zzb.model.system.SysRole;
import com.zzb.system.service.SysRoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SysRoleServiceTest {
    @Autowired
    private SysRoleService sysRoleService;

    @Test
    public void findAll(){
        List<SysRole> list = sysRoleService.list();
        System.out.println(list);
    }
}
