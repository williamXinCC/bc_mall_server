package com.william.bc_mall_server.mapper;

import java.util.List;

import com.william.bcpojo.WilliamDept;
import com.william.bcpojo.WilliamDeptExample;
import org.apache.ibatis.annotations.Param;

public interface WilliamDeptMapper {
    int countByExample(WilliamDeptExample example);

    int deleteByExample(WilliamDeptExample example);

    int deleteByPrimaryKey(Integer deptId);

    int insert(WilliamDept record);

    int insertSelective(WilliamDept record);

    List<WilliamDept> selectByExample(WilliamDeptExample example);

    WilliamDept selectByPrimaryKey(Integer deptId);

    int updateByExampleSelective(@Param("record") WilliamDept record, @Param("example") WilliamDeptExample example);

    int updateByExample(@Param("record") WilliamDept record, @Param("example") WilliamDeptExample example);

    int updateByPrimaryKeySelective(WilliamDept record);

    int updateByPrimaryKey(WilliamDept record);

    List<WilliamDept> selectPage(WilliamDeptExample example);
}