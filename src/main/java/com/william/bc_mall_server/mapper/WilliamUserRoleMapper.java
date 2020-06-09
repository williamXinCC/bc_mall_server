package com.william.bc_mall_server.mapper;

import java.util.List;

import com.william.bcpojo.WilliamUserRole;
import com.william.bcpojo.WilliamUserRoleExample;
import org.apache.ibatis.annotations.Param;

public interface WilliamUserRoleMapper {
    int countByExample(WilliamUserRoleExample example);

    int deleteByExample(WilliamUserRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WilliamUserRole record);

    int insertSelective(WilliamUserRole record);

    List<WilliamUserRole> selectByExample(WilliamUserRoleExample example);

    WilliamUserRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WilliamUserRole record, @Param("example") WilliamUserRoleExample example);

    int updateByExample(@Param("record") WilliamUserRole record, @Param("example") WilliamUserRoleExample example);

    int updateByPrimaryKeySelective(WilliamUserRole record);

    int updateByPrimaryKey(WilliamUserRole record);

    List<String> getRoleListByUid(String userId);
}