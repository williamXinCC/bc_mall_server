package com.william.bc_mall_server.controller;

import cn.hutool.json.JSONUtil;
import com.william.bc_mall_server.service.CategoryService;
import com.william.pojo.Result;
import com.william.pojo.WilliamCategory;
import com.william.tree.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xinchuang
 * @version v1.0
 * @date 2020/6/16 14:32
 * @since Copyright(c) 爱睿智健康科技
 */
@RestController
@RequestMapping("/william-caterogy")
public class ProductCategoryController {

    @Autowired
    private CategoryService categoryService;

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
