package com.william.bc_mall_server.controller;

import com.william.bc_mall_server.service.BrandService;
import com.william.bcpojo.vo.BrandVo;
import com.william.constant.RespCodeAndMsg;
import com.william.pojo.Result;
import com.william.pojo.WilliamBrand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author xinchuang
 * @version v1.0
 * @date 2020/6/17 9:21
 * @since Copyright(c) 爱睿智健康科技
 */
@RestController
@RequestMapping("/william-brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    /**
     * 所有品牌,没有条件
     * @author     xinchuang
     * @return : com.william.pojo.Result
     */
    @PostMapping(value = "/getAllBrand")
    public Result getAllBrand(){
        List<WilliamBrand> allBrand = brandService.getAllBrand();
        return new Result(allBrand);
    }

    /**
     * 删除分类
     * @author     xinchuang
     * @param williamBrand :
     * @return : com.william.pojo.Result
     */
    @RequestMapping("/deleteBrandById")
    public Result deleteBrandById(WilliamBrand williamBrand) {
        brandService.deleteBrandById(williamBrand.getId());
        return Result.getResult(RespCodeAndMsg.DELETE_SUCCESS);
    }


    /**
     * 修改分类
     * @author     xinchuang
     * @param williamBrand :
     * @return : com.william.pojo.Result
     */
    @PostMapping(value = "/updateBrand")
    public Result updateBrand(WilliamBrand williamBrand){
        brandService.updateBrand(williamBrand);
        return Result.getResult(RespCodeAndMsg.UPDATE_SUCCESS);
    }

    /**
     * 分类列表
     * @author     xinchuang
     * @param categoryVo :
     * @return : com.william.pojo.Result
     */
    @GetMapping(value = "/getBrandList")
    public Result getBrandList(BrandVo categoryVo){
        return brandService.getBrandList(categoryVo);
    }


    /**
     * 添加分类
     * @author     xinchuang
     * @param williamBrand :
     * @return : com.william.pojo.Result
     */
    @PostMapping(value = "/addBrand")
    public Result addBrand(WilliamBrand williamBrand){
        brandService.addBrand(williamBrand);
        return Result.getResult(RespCodeAndMsg.ADD_SUCCESS);
    }

}
