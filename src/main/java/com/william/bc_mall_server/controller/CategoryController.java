package com.william.bc_mall_server.controller;

import cn.hutool.json.JSONUtil;
import com.william.bc_mall_server.service.CategoryService;
import com.william.bcpojo.vo.CategoryVo;
import com.william.constant.RespCodeAndMsg;
import com.william.pojo.Result;
import com.william.pojo.WilliamCategory;
import com.william.tree.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author xinchuang
 * @version v1.0
 * @date 2020/6/16 14:32
 * @since Copyright(c) 爱睿智健康科技
 */
@RestController
@RequestMapping("/william-category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 查询当前的ID的菜单有没有子菜单
     */
    @RequestMapping("/getCategoryHasChildrenNode")
    public Map<String,Object> getCategoryHasChildrenNode(Integer id){
        return categoryService.getCategoryHasChildrenNode(id);
    }

    /**
     * 删除分类
     * @author     xinchuang
     * @param williamCategory :
     * @return : com.william.pojo.Result
     */
    @RequestMapping("/deleteCategoryById")
    public Result deleteCategoryById(WilliamCategory williamCategory) {
        categoryService.deleteCategoryById(williamCategory.getId());
        return Result.getResult(RespCodeAndMsg.DELETE_SUCCESS);
    }



    /**
     * 修改分类
     * @author     xinchuang
     * @param williamCategory :
     * @return : com.william.pojo.Result
     */
    @PostMapping(value = "/updateCategory")
    public Result updateCategory(WilliamCategory williamCategory){
        categoryService.updateCategory(williamCategory);
        return Result.getResult(RespCodeAndMsg.UPDATE_SUCCESS);
    }

    /**
     * 分类列表
     * @author     xinchuang
     * @param categoryVo :
     * @return : com.william.pojo.Result
     */
    @GetMapping(value = "/getCategoryList")
    public Result getCategoryList(CategoryVo categoryVo){
        return categoryService.getCategoryList(categoryVo);
    }


    /**
     * 添加分类
     * @author     xinchuang
     * @param williamCategory :
     * @return : com.william.pojo.Result
     */
    @PostMapping(value = "/addCategory")
    public Result addCategory(WilliamCategory williamCategory){
        categoryService.addCategory(williamCategory);
        return Result.getResult(RespCodeAndMsg.ADD_SUCCESS);
    }

    /**
     *  菜单树
     */
    @RequestMapping("/getCategoryLeftTreeJson")
    public Result getCategoryLeftTreeJson() {
        List<WilliamCategory> list = categoryService.getAllCategory();
        List<TreeNode> treeNodes = new ArrayList<>();
        for (WilliamCategory williamCategory: list) {
            Boolean spread = williamCategory.getSpread() == 1;
            treeNodes.add(new TreeNode(williamCategory.getId(),williamCategory.getPid(),williamCategory.getName(),spread,williamCategory.getSeq()));
        }
        System.out.println(JSONUtil.toJsonStr(treeNodes));
        treeNodes.sort((a, b) -> a.getSeq() - b.getSeq());
        return new Result(treeNodes);
    }
}
