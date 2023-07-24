package com.zzb.system.controller;

import com.zzb.common.result.Result;
import com.zzb.common.util.JwtHelper;
import com.zzb.model.system.SysUser;
import com.zzb.model.vo.LoginVo;
import com.zzb.system.exception.ZBException;
import com.zzb.system.service.SysUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "用户登录接口")
@RestController
@RequestMapping("/admin/system/index")
public class IndexController {
    @Autowired
    private SysUserService sysUserService;
    //login
    @PostMapping("login")
    public Result login(@RequestBody LoginVo loginVo){
        //根据username查询数据
        SysUser sysUser=sysUserService.getUserInfoByUserName(loginVo.getUsername());
        //如果查询为空
        if(sysUser==null){
            throw new ZBException(20001,"用户不存在");
        }
        //判断密码是否一致
        String password= DigestUtils.md5DigestAsHex(loginVo.getPassword().getBytes());
        if(!sysUser.getPassword().equals(password)){
            throw new ZBException(20001,"密码不正确");
        }
        //判断用户是否可用
        if(sysUser.getStatus().intValue()==0){
            throw new ZBException(20001,"用户已禁用");
        }
        //根据userid和username生成token字符串
        String token = JwtHelper.createToken(sysUser.getId(), sysUser.getUsername());
        HashMap<String,Object> map=new HashMap();
        map.put("token",token);
        return Result.ok(map);
    }
    //info
    @GetMapping("info")
    public Result info(HttpServletRequest request){
        /*1.获取请求头中token字符串
        * 2.从token中获取用户名称 （id）
        * 3.根据用户名称获取到用户信息（基本信息 菜单信息 按钮权限数据）
        * */

        HashMap<String,Object> map=new HashMap<>();
        String token = request.getHeader("token");
        String username = JwtHelper.getUsername(token);
        Map<String, Object> userInfo = sysUserService.getUserInfo(username);


        map.put("roles","[admin]");
        map.put("introduction","I am a super administrator");
        map.put("avatar","https://thirdwx.qlogo.cn/mmopen/vi_32/kj8UwADicsWCCsVnB00RJt7ZnUYppia7aQMJibpmSVhhISfIr2Ew7eRd2n2e6fxqVqicXxiaNt6d671oNu2KRPkvYtA/132 ");
        map.put("name","Super admin zzb");
        return Result.ok(map);
    }
}
