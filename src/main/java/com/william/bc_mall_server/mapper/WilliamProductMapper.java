package com.william.bc_mall_server.mapper;

import java.util.List;

import com.william.pojo.WilliamProduct;
import com.william.pojo.WilliamProductExample;
import org.apache.ibatis.annotations.Param;

public interface WilliamProductMapper {
    int countByExample(WilliamProductExample example);

    int deleteByExample(WilliamProductExample example);

    int deleteByPrimaryKey(String id);

    int insert(WilliamProduct record);

    int insertSelective(WilliamProduct record);

    List<WilliamProduct> selectByExample(WilliamProductExample example);

    WilliamProduct selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") WilliamProduct record, @Param("example") WilliamProductExample example);

    int updateByExample(@Param("record") WilliamProduct record, @Param("example") WilliamProductExample example);

    int updateByPrimaryKeySelective(WilliamProduct record);

    int updateByPrimaryKey(WilliamProduct record);
}