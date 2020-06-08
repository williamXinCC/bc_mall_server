package com.william.bc_mall_server.mapper;

import java.util.List;

import com.william.bcpojo.WilliamMenu;
import com.william.bcpojo.WilliamMenuExample;
import org.apache.ibatis.annotations.Param;

public interface WilliamMenuMapper {
    int countByExample(WilliamMenuExample example);

    int deleteByExample(WilliamMenuExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WilliamMenu record);

    int insertSelective(WilliamMenu record);

    List<WilliamMenu> selectByExample(WilliamMenuExample example);

    WilliamMenu selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WilliamMenu record, @Param("example") WilliamMenuExample example);

    int updateByExample(@Param("record") WilliamMenu record, @Param("example") WilliamMenuExample example);

    int updateByPrimaryKeySelective(WilliamMenu record);

    int updateByPrimaryKey(WilliamMenu record);
}