package com.william.bc_mall_server.mapper;

import java.util.List;

import com.william.bcpojo.WilliamUser;
import com.william.bcpojo.WilliamUserExample;
import org.apache.ibatis.annotations.Param;

public interface WilliamUserMapper {
    int countByExample(WilliamUserExample example);

    int deleteByExample(WilliamUserExample example);

    int deleteByPrimaryKey(String userId);

    int insert(WilliamUser record);

    int insertSelective(WilliamUser record);

    List<WilliamUser> selectByExample(WilliamUserExample example);

    WilliamUser selectByPrimaryKey(String userId);

    int updateByExampleSelective(@Param("record") WilliamUser record, @Param("example") WilliamUserExample example);

    int updateByExample(@Param("record") WilliamUser record, @Param("example") WilliamUserExample example);

    int updateByPrimaryKeySelective(WilliamUser record);

    int updateByPrimaryKey(WilliamUser record);
}