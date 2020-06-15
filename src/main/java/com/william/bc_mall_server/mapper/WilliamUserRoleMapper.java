package com.william.bc_mall_server.mapper;

import java.util.Date;
import java.util.List;

import com.william.bcpojo.WilliamUserRole;
import com.william.bcpojo.WilliamUserRoleExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

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

    List<Integer> getRoleCodeListByUid(String userId);

    void inserBatch(@Param("userId") String userId, @Param("list") List<String> strings, @Param("createId") String createId,@Param("createTime") Date date);

    void deleteByUserId(String userId);
}