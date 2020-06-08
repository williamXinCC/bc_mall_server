package com.william.bc_mall_server.mapper;

import java.util.List;

import com.william.bcpojo.WilliamRole;
import com.william.bcpojo.WilliamRoleExample;
import org.apache.ibatis.annotations.Param;

public interface WilliamRoleMapper {
    int countByExample(WilliamRoleExample example);

    int deleteByExample(WilliamRoleExample example);

    int deleteByPrimaryKey(Integer roleId);

    int insert(WilliamRole record);

    int insertSelective(WilliamRole record);

    List<WilliamRole> selectByExample(WilliamRoleExample example);

    WilliamRole selectByPrimaryKey(Integer roleId);

    int updateByExampleSelective(@Param("record") WilliamRole record, @Param("example") WilliamRoleExample example);

    int updateByExample(@Param("record") WilliamRole record, @Param("example") WilliamRoleExample example);

    int updateByPrimaryKeySelective(WilliamRole record);

    int updateByPrimaryKey(WilliamRole record);
}