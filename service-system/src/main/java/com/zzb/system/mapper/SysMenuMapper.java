package com.zzb.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzb.model.system.SysMenu;
import com.zzb.model.vo.RouterVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author zzb
 * @since 2023-05-08
 */
@Repository
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<RouterVo> findMenuListUserID(String id);
}
