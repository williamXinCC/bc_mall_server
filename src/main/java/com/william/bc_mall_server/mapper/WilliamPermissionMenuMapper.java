package com.william.bc_mall_server.mapper;

import java.util.List;

import com.william.bcpojo.WilliamPermissionMenu;
import com.william.bcpojo.WilliamPermissionMenuExample;
import org.apache.ibatis.annotations.Param;

public interface WilliamPermissionMenuMapper {
    int countByExample(WilliamPermissionMenuExample example);

    int deleteByExample(WilliamPermissionMenuExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WilliamPermissionMenu record);

    int insertSelective(WilliamPermissionMenu record);

    List<WilliamPermissionMenu> selectByExample(WilliamPermissionMenuExample example);

    WilliamPermissionMenu selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WilliamPermissionMenu record, @Param("example") WilliamPermissionMenuExample example);

    int updateByExample(@Param("record") WilliamPermissionMenu record, @Param("example") WilliamPermissionMenuExample example);

    int updateByPrimaryKeySelective(WilliamPermissionMenu record);

    int updateByPrimaryKey(WilliamPermissionMenu record);
}