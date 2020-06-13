package com.william.bc_mall_server.service.impl;

import com.william.bc_mall_server.mapper.WilliamMenuMapper;
import com.william.bc_mall_server.service.WilliamMenuService;
import com.william.bcconstant.BcConsts;
import com.william.bcpojo.WilliamMenu;
import com.william.bcpojo.WilliamMenuExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @author xinchuang
 * @version v1.0
 * @date 2020/6/9 17:06
 * @since Copyright(c) 爱睿智健康科技
 */
@Service
public class WilliamMenuServiceImpl implements WilliamMenuService {

    @Autowired
    private WilliamMenuMapper williamMenuMapper;


    /**
     * 查所有
     * @author     xinchuang
     * @param type :
     * @return : java.util.List<com.william.bcpojo.WilliamMenu>
     */
    @Override
    public List<WilliamMenu> getMenusByType(Integer type) {
        WilliamMenuExample williamMenuExample = new WilliamMenuExample();
        WilliamMenuExample.Criteria criteria = williamMenuExample.createCriteria();
        criteria.andTypeEqualTo(type);
        criteria.andStatusEqualTo(1);
        criteria.andShowFlagEqualTo(1);
        criteria.andTenantIdEqualTo(BcConsts.TENANT_ID);
        williamMenuExample.setOrderByClause("seq asc");
        return williamMenuMapper.selectByExample(williamMenuExample);
    }

    /**
     * ids查询菜单
     * @author     xinchuang
     * @param menuList :
     * @param type :
     * @return : java.util.List<com.william.bcpojo.WilliamMenu>
     */
    @Override
    public List<WilliamMenu> getMenusByIds(List<Integer> menuList,Integer type) {
        WilliamMenuExample williamMenuExample = new WilliamMenuExample();
        WilliamMenuExample.Criteria criteria = williamMenuExample.createCriteria();
        if(0 != type){
            criteria.andTypeEqualTo(type);
        }
        criteria.andStatusEqualTo(1);
        criteria.andShowFlagEqualTo(1);
        criteria.andTenantIdEqualTo(BcConsts.TENANT_ID);
        criteria.andMenuIdIn(menuList);
        williamMenuExample.setOrderByClause("seq asc");
        return williamMenuMapper.selectByExample(williamMenuExample);
    }
}
