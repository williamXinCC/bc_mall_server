package com.william.bc_mall_server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.william.bc_mall_server.mapper.WilliamAttrCategoryMapper;
import com.william.bc_mall_server.mapper.WilliamAttrNameMapper;
import com.william.bc_mall_server.mapper.WilliamAttrValueMapper;
import com.william.bc_mall_server.mapper.WilliamBaseAttrMapper;
import com.william.bc_mall_server.service.AttrService;
import com.william.bcpojo.vo.AttrVo;
import com.william.constant.Constant;
import com.william.pojo.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author xinchuang
 * @version v1.0
 * @date 2020/6/23 13:32
 * @since Copyright(c) 爱睿智健康科技
 */
@Service
public class AttrServiceImpl implements AttrService {

    @Autowired
    private WilliamAttrNameMapper nameMapper;

    @Autowired
    private WilliamAttrValueMapper valueMapper;

    @Autowired
    private WilliamAttrCategoryMapper attrCategoryMapper;

    @Autowired
    private WilliamBaseAttrMapper baseAttrMapper;


    @Override
    public void addAttr(AttrVo attrVo) {
        // 保存属性名
        nameMapper.insertSelective(attrVo);
        Long nId = attrVo.getnId();
        // 保存属性值
        String attrValue = attrVo.getAttrValue();
        String[] split = attrValue.split(",");
        WilliamAttrValue williamAttrValue = new WilliamAttrValue();
        williamAttrValue.setnId(Math.toIntExact(nId));
        williamAttrValue.setCreateTime(new Date());
        valueMapper.insertAttrValueByBatch(williamAttrValue, Arrays.asList(split));
    }

    /**
     * 条件查询属性列表
     * @author     xinchuang
     * @param attrVo :
     * @return : com.william.pojo.Result
     */
    @Override
    public Result getAttrListByCondition(AttrVo attrVo) {
        WilliamAttrNameExample williamAttrNameExample = new WilliamAttrNameExample();
        WilliamAttrNameExample.Criteria criteria = williamAttrNameExample.createCriteria();
        if(Objects.nonNull(attrVo.getcId())){
            criteria.andCIdEqualTo(attrVo.getcId());
        }
        if(StringUtils.isNotBlank(attrVo.getnName())){
            criteria.andNNameLike(attrVo.getnName());
        }
        if(StringUtils.isNotBlank(attrVo.getpId())){
            criteria.andPIdEqualTo(attrVo.getpId());
        }
        criteria.andStatusEqualTo(Constant.STATUS_ONE_USE);
        PageHelper.startPage(attrVo.getPage(),attrVo.getLimit());
        List<WilliamAttrName> williamAttrNames = nameMapper.selectByExample(williamAttrNameExample);
        PageInfo<WilliamAttrName> pageInfo = new PageInfo<>(williamAttrNames);
        return new Result(pageInfo.getTotal(),williamAttrNames);
    }

    @Override
    public void deleteAttrById(Long aLong) {
        WilliamAttrName williamAttrName = new WilliamAttrName();
        williamAttrName.setnId(aLong);
        williamAttrName.setStatus(2);
        nameMapper.updateByPrimaryKeySelective(williamAttrName);
        WilliamAttrValue williamAttrValue = new WilliamAttrValue();
        williamAttrValue.setStatus(2);
        WilliamAttrValueExample williamAttrValueExample = new WilliamAttrValueExample();
        WilliamAttrValueExample.Criteria criteria = williamAttrValueExample.createCriteria();
        criteria.andNIdEqualTo(Math.toIntExact(aLong));
        valueMapper.updateByExampleSelective(williamAttrValue,williamAttrValueExample);
    }

    @Override
    public void updateAttr(AttrVo attrVo) {
        nameMapper.updateByPrimaryKeySelective(attrVo);
    }
}
