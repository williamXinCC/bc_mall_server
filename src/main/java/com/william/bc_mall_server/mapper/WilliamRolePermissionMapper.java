package com.william.bc_mall_server.mapper;

import java.util.Date;
import java.util.List;

import com.william.bcpojo.WilliamRolePermission;
import com.william.bcpojo.WilliamRolePermissionExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;

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

    List<Integer> getPermissionListByRoleId(Integer roleId);

    void inserBatchRolePermission(@Param("roleId") String roleId,@Param("date") Date date,@Param("list") Integer[] ids);
}