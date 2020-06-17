package com.william.bc_mall_server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.william.bc_mall_server.mapper.WilliamBrandMapper;
import com.william.bc_mall_server.service.BrandService;
import com.william.bcpojo.vo.BrandVo;
import com.william.constant.Constant;
import com.william.pojo.Result;
import com.william.pojo.WilliamBrand;
import com.william.pojo.WilliamBrandExample;
import com.william.utils.ObjectId;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author xinchuang
 * @version v1.0
 * @date 2020/6/17 9:27
 * @since Copyright(c) 爱睿智健康科技
 */
@Service
public class BrandServiceImpl implements BrandService {
    
    @Autowired
    private WilliamBrandMapper brandMapper;

    /**
     * 查询所有分类
     * @author     xinchuang
     * @return : java.util.List<com.william.pojo.WilliamBrand>
     */
    @Override
    public List<WilliamBrand> getAllBrand() {
        return brandMapper.getAllBrand();
    }

    @Override
    public void addBrand(WilliamBrand williamBrand) {
        williamBrand.setId(ObjectId.get());
        brandMapper.insertSelective(williamBrand);
    }

    @Override
    public Result getBrandList(BrandVo brandVo) {
        WilliamBrandExample williamBrandExample = new WilliamBrandExample();
        WilliamBrandExample.Criteria criteria = williamBrandExample.createCriteria();
        String name = brandVo.getChName();
        if(StringUtils.isNotBlank(name)){
            criteria.andChNameEqualTo(name);
        }
        if(Objects.nonNull(brandVo.getcId()) && 0 != brandVo.getcId()){
            criteria.andCIdEqualTo(brandVo.getcId());
        }
        criteria.andStatusEqualTo(Constant.STATUS_ONE_USE);
        PageHelper.startPage(brandVo.getPage(),brandVo.getLimit());
        List<WilliamBrand> williamCategories = brandMapper.selectByExample(williamBrandExample);
        PageInfo<WilliamBrand> pageInfo = new PageInfo<>(williamCategories);
        return new Result(pageInfo.getTotal(),williamCategories);
    }

    @Override
    public void deleteBrandById(String id) {
        WilliamBrand williamBrand = new WilliamBrand();
        williamBrand.setId(id);
        williamBrand.setStatus(99);
        brandMapper.updateByPrimaryKeySelective(williamBrand);
    }

    @Override
    public void updateBrand(WilliamBrand williamBrand) {
        williamBrand.setModTime(new Date());
        brandMapper.updateByPrimaryKeySelective(williamBrand);
    }
}
