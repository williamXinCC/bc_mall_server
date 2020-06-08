package com.william.bc_mall_server.mapper;

import java.util.List;

import com.william.bcpojo.WilliamOperationLog;
import com.william.bcpojo.WilliamOperationLogExample;
import org.apache.ibatis.annotations.Param;

public interface WilliamOperationLogMapper {
    int countByExample(WilliamOperationLogExample example);

    int deleteByExample(WilliamOperationLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WilliamOperationLog record);

    int insertSelective(WilliamOperationLog record);

    List<WilliamOperationLog> selectByExample(WilliamOperationLogExample example);

    WilliamOperationLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WilliamOperationLog record, @Param("example") WilliamOperationLogExample example);

    int updateByExample(@Param("record") WilliamOperationLog record, @Param("example") WilliamOperationLogExample example);

    int updateByPrimaryKeySelective(WilliamOperationLog record);

    int updateByPrimaryKey(WilliamOperationLog record);
}