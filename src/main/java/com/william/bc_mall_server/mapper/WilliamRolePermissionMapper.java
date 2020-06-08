package com.william.bc_mall_server.mapper;

import java.util.List;

import com.william.bcpojo.WilliamRolePermission;
import com.william.bcpojo.WilliamRolePermissionExample;
import org.apache.ibatis.annotations.Param;

public interface WilliamRolePermissionMapper {
    int countByExample(WilliamRolePermissionExample example);

    int deleteByExample(WilliamRolePermissionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WilliamRolePermission record);

    int insertSelective(WilliamRolePermission record);

    List<WilliamRolePermission> selectByExample(WilliamRolePermissionExample example);

    WilliamRolePermission selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WilliamRolePermission record, @Param("example") WilliamRolePermissionExample example);

    int updateByExample(@Param("record") WilliamRolePermission record, @Param("example") WilliamRolePermissionExample example);

    int updateByPrimaryKeySelective(WilliamRolePermission record);

    int updateByPrimaryKey(WilliamRolePermission record);
}