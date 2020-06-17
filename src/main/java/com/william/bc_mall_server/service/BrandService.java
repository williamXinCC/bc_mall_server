package com.william.bc_mall_server.service;

import com.william.bcpojo.vo.BrandVo;
import com.william.pojo.Result;
import com.william.pojo.WilliamBrand;

import java.util.List;

/**
 * @author xinchuang
 * @version v1.0
 * @date 2020/6/17 9:26
 * @since Copyright(c) 爱睿智健康科技
 */
public interface BrandService {

    List<WilliamBrand> getAllBrand();

    void addBrand(WilliamBrand williamBrand);

    Result getBrandList(BrandVo categoryVo);

    void deleteBrandById(String id);

    void updateBrand(WilliamBrand williamBrand);

}
