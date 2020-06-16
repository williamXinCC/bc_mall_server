package com.william.bc_mall_server.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.william.bc_mall_server.service.ProductService;
import com.william.bcpojo.vo.ProductVo;
import com.william.constant.Constant;
import com.william.constant.RespCodeAndMsg;
import com.william.pojo.Result;
import com.william.pojo.WilliamGoods;
import com.william.pojo.WilliamGoodsCategory;
import com.william.pojo.WilliamProduct;
import com.william.utils.ObjectId;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 * @author xinchuang
 * @version v1.0
 * @date 2020/6/16 14:33
 * @since Copyright(c) 爱睿智健康科技
 */
@RestController
@RequestMapping("/william-product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 商品列表
     * @author     xinchuang
     * @return : com.william.pojo.Result
     */
    @GetMapping(value = "/getProductList")
    public Result getGoodsList(ProductVo productVo){
        return productService.getProductList(productVo);
    }

    /**
     * 添加商品
     * @author     xinchuang
     * @return : com.william.pojo.Result
     */
    @PostMapping(value = "/addProduct")
    public Result addProduct(WilliamProduct williamProduct) {
        Integer categoryId = williamProduct.getcId();
        if(Objects.isNull(categoryId) || Objects.equals(categoryId,0)){
            return Result.getResult(RespCodeAndMsg.ADD_ERROR,"商品分类为空");
        }
        williamProduct.setId(ObjectId.get());
        productService.addProduct(williamProduct);
        return Result.getResult(RespCodeAndMsg.ADD_SUCCESS);
    }
}
