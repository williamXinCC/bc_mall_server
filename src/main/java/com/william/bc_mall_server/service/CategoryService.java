package com.william.bc_mall_server.service;

import com.william.bcpojo.vo.CategoryVo;
import com.william.pojo.Result;
import com.william.pojo.WilliamCategory;

import java.util.List;
import java.util.Map;

/**
 * @author xinchuang
 * @version v1.0
 * @date 2020/6/16 15:09
 * @since Copyright(c) 爱睿智健康科技
 */
public interface CategoryService {

    List<WilliamCategory> getAllCategory();

    void addCategory(WilliamCategory williamCategory);

    Result getCategoryList(CategoryVo categoryVo);

    void deleteCategoryById(Integer id);

    void updateCategory(WilliamCategory williamCategory);

    Map<String,Object> getCategoryHasChildrenNode(Integer id);
}
