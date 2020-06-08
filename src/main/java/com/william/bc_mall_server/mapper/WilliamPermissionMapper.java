package com.william.bc_mall_server.mapper;

import java.util.List;

import com.william.bcpojo.WilliamPermission;
import com.william.bcpojo.WilliamPermissionExample;
import org.apache.ibatis.annotations.Param;

public interface WilliamPermissionMapper {
    int countByExample(WilliamPermissionExample example);

    int deleteByExample(WilliamPermissionExample example);

    int deleteByPrimaryKey(Integer permissionId);

    int insert(WilliamPermission record);

    int insertSelective(WilliamPermission record);

    List<WilliamPermission> selectByExample(WilliamPermissionExample example);

    WilliamPermission selectByPrimaryKey(Integer permissionId);

    int updateByExampleSelective(@Param("record") WilliamPermission record, @Param("example") WilliamPermissionExample example);

    int updateByExample(@Param("record") WilliamPermission record, @Param("example") WilliamPermissionExample example);

    int updateByPrimaryKeySelective(WilliamPermission record);

    int updateByPrimaryKey(WilliamPermission record);
}