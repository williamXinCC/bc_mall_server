package com.william.bc_mall_server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.william.bc_mall_server.mapper.WilliamBrandMapper;
import com.william.bc_mall_server.mapper.WilliamCategoryMapper;
import com.william.bc_mall_server.mapper.WilliamProductMapper;
import com.william.bc_mall_server.service.ProductService;
import com.william.bcpojo.vo.ProductVo;
import com.william.pojo.Result;
import com.william.pojo.WilliamProduct;
import com.william.pojo.WilliamProductExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * 商品管理
 * @author xinchuang
 * @version v1.0
 * @date 2020/6/16 15:05
 * @since Copyright(c) 爱睿智健康科技
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private WilliamProductMapper productMapper;

    @Autowired
    private WilliamCategoryMapper categoryMapper;

    @Autowired
    private WilliamBrandMapper brandMapper;

    @Override
    public void addProduct(WilliamProduct williamProduct) {
        productMapper.insertSelective(williamProduct);
    }

    @Override
    public Result getProductList(ProductVo productVo) {
        WilliamProductExample williamProductExample = new WilliamProductExample();
        WilliamProductExample.Criteria criteria = williamProductExample.createCriteria();
        // 分类
        if(Objects.nonNull(productVo.getcId()) && 0 != productVo.getcId()){
            criteria.andCIdEqualTo(productVo.getcId());
        }
        // 品牌
        if(StringUtils.isNotBlank(productVo.getbId())){
            criteria.andBIdEqualTo(productVo.getbId());
        }
        PageHelper.startPage(productVo.getPage(),productVo.getLimit());
        List<WilliamProduct> williamProducts = productMapper.selectByExample(williamProductExample);
        PageInfo<WilliamProduct> pageInfo = new PageInfo<>(williamProducts);
        return new Result(pageInfo.getTotal(),williamProducts);
    }
}
