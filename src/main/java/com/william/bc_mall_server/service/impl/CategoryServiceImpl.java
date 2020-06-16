package com.william.bc_mall_server.service.impl;

import com.william.bc_mall_server.mapper.WilliamCategoryMapper;
import com.william.bc_mall_server.service.CategoryService;
import com.william.bcconstant.BcConsts;
import com.william.constant.Constant;
import com.william.pojo.WilliamCategory;
import com.william.pojo.WilliamCategoryExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
