package com.william.bc_mall_server.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.william.bc_mall_server.mapper.WilliamCategoryMapper;
import com.william.bc_mall_server.service.CategoryService;
import com.william.bcpojo.vo.CategoryVo;
import com.william.constant.Constant;
import com.william.pojo.Result;
import com.william.pojo.WilliamCategory;
import com.william.pojo.WilliamCategoryExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 商品分类管理
 * @author xinchuang
 * @version v1.0
 * @date 2020/6/16 15:10
 * @since Copyright(c) 爱睿智健康科技
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private WilliamCategoryMapper categoryMapper;

    /**
     * 查询所有分类
     * @author     xinchuang
     * @return : java.util.List<com.william.pojo.WilliamCategory>
     */
    @Override
    public List<WilliamCategory> getAllCategory() {
        return categoryMapper.getAllCategory();
    }

    @Override
    public void addCategory(WilliamCategory williamCategory) {
        categoryMapper.insertSelective(williamCategory);
    }

    @Override
    public Result getCategoryList(CategoryVo categoryVo) {
        WilliamCategoryExample williamCategoryExample = new WilliamCategoryExample();
        WilliamCategoryExample.Criteria criteria = williamCategoryExample.createCriteria();
        WilliamCategoryExample.Criteria criteriaA = williamCategoryExample.createCriteria();
        String name = categoryVo.getName();
        if(StringUtils.isNotBlank(name)){
            criteria.andNameEqualTo(name);
        }
        if(Objects.nonNull(categoryVo.getId()) && 0 != categoryVo.getId()){
            criteria.andPidEqualTo(categoryVo.getId());
            criteriaA.andIdEqualTo(categoryVo.getId());
            williamCategoryExample.or(criteriaA);
        }
        criteria.andStatusEqualTo(Constant.STATUS_ONE_USE);
        PageHelper.startPage(categoryVo.getPage(),categoryVo.getLimit());
        List<WilliamCategory> williamCategories = categoryMapper.selectByExample(williamCategoryExample);
        PageInfo<WilliamCategory> pageInfo = new PageInfo<>(williamCategories);
        return new Result(pageInfo.getTotal(),williamCategories);
    }

    @Override
    public void deleteCategoryById(Integer id) {
        WilliamCategory williamCategory = new WilliamCategory();
        williamCategory.setId(id);
        williamCategory.setStatus(99);
        categoryMapper.updateByPrimaryKeySelective(williamCategory);
    }

    @Override
    public void updateCategory(WilliamCategory williamCategory) {
        williamCategory.setModTime(new Date());
        categoryMapper.updateByPrimaryKeySelective(williamCategory);
    }

    @Override
    public Map<String, Object> getCategoryHasChildrenNode(Integer id) {
        Map<String, Object> map=new HashMap<String, Object>();
        WilliamCategoryExample williamCategoryExample = new WilliamCategoryExample();
        WilliamCategoryExample.Criteria criteria = williamCategoryExample.createCriteria();
        criteria.andPidEqualTo(id);
        List<WilliamCategory> list = categoryMapper.selectByExample(williamCategoryExample);
        if(CollectionUtil.isNotEmpty(list)) {
            map.put("value", true);
        }else {
            map.put("value", false);
        }
        return map;
    }
}
