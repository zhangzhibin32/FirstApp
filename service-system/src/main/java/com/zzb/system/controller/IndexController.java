package com.zzb.system.controller;

import com.zzb.common.result.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Api(tags = "用户登录接口")
@RestController
@RequestMapping("/admin/system/index")
public class IndexController {
    //login
    @PostMapping("login")
    public Result login(){
        HashMap<String,Object> map=new HashMap();
        map.put("token","admin-token");
        return Result.ok(map);
    }
    //info
    @GetMapping("info")
    public Result info(){
        HashMap<String,Object> map=new HashMap<>();
        map.put("roles","[admin]");
        map.put("introduction","I am a super administrator");
        map.put("avatar","https://thirdwx.qlogo.cn/mmopen/vi_32/kj8UwADicsWCCsVnB00RJt7ZnUYppia7aQMJibpmSVhhISfIr2Ew7eRd2n2e6fxqVqicXxiaNt6d671oNu2KRPkvYtA/132 ");
        map.put("name","Super admin zzb");
        return Result.ok(map);
    }
}
