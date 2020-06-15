package com.william.bc_mall_server.mapper;

import java.util.List;

import com.william.bcpojo.WilliamPermission;
import com.william.bcpojo.WilliamPermissionExample;
import org.apache.ibatis.annotations.Param;

public interface WilliamPermissionMapper {
    int countByExample(WilliamPermissionExample example);

    int deleteByExample(WilliamPermissionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WilliamPermission record);

    int insertSelective(WilliamPermission record);

    List<WilliamPermission> selectByExample(WilliamPermissionExample example);

    WilliamPermission selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WilliamPermission record, @Param("example") WilliamPermissionExample example);

    int updateByExample(@Param("record") WilliamPermission record, @Param("example") WilliamPermissionExample example);

    int updateByPrimaryKeySelective(WilliamPermission record);

    int updateByPrimaryKey(WilliamPermission record);

    List<WilliamPermission> selectPage(WilliamPermissionExample example);

    List<WilliamPermission> getAllPermissionAndMenus();

    List<WilliamPermission> getAllPermission(Integer id);

    List<WilliamPermission> selectMenusByPid(Integer pid);
}