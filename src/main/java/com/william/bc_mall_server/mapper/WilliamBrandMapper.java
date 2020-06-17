package com.william.bc_mall_server.mapper;

import java.util.List;

import com.william.pojo.WilliamBrand;
import com.william.pojo.WilliamBrandExample;
import org.apache.ibatis.annotations.Param;

public interface WilliamBrandMapper {
    int countByExample(WilliamBrandExample example);

    int deleteByExample(WilliamBrandExample example);

    int deleteByPrimaryKey(String id);

    int insert(WilliamBrand record);

    int insertSelective(WilliamBrand record);

    List<WilliamBrand> selectByExample(WilliamBrandExample example);

    WilliamBrand selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") WilliamBrand record, @Param("example") WilliamBrandExample example);

    int updateByExample(@Param("record") WilliamBrand record, @Param("example") WilliamBrandExample example);

    int updateByPrimaryKeySelective(WilliamBrand record);

    int updateByPrimaryKey(WilliamBrand record);

    List<WilliamBrand> getAllBrand();
}