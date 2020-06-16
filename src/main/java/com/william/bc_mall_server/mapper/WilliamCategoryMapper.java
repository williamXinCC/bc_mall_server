package com.william.bc_mall_server.mapper;

import java.util.List;

import com.william.pojo.WilliamCategory;
import com.william.pojo.WilliamCategoryExample;
import org.apache.ibatis.annotations.Param;

public interface WilliamCategoryMapper {
    int countByExample(WilliamCategoryExample example);

    int deleteByExample(WilliamCategoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WilliamCategory record);

    int insertSelective(WilliamCategory record);

    List<WilliamCategory> selectByExample(WilliamCategoryExample example);

    WilliamCategory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WilliamCategory record, @Param("example") WilliamCategoryExample example);

    int updateByExample(@Param("record") WilliamCategory record, @Param("example") WilliamCategoryExample example);

    int updateByPrimaryKeySelective(WilliamCategory record);

    int updateByPrimaryKey(WilliamCategory record);

    List<WilliamCategory> getAllCategory();
}