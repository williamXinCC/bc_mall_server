package com.william.bc_mall_server.service.impl;

import com.william.bc_mall_server.mapper.WilliamMenuMapper;
import com.william.bc_mall_server.service.WilliamMenuService;
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

    @Override
    public List<WilliamMenu> getMenusByIds(Set<Integer> menuList,Integer type) {
        Integer[] menuIds = (Integer[]) menuList.toArray();
        WilliamMenuExample williamMenuExample = new WilliamMenuExample();
        WilliamMenuExample.Criteria criteria = williamMenuExample.createCriteria();
        if(0 != type){
            criteria.andTypeEqualTo(type);
        }
        criteria.andStatusEqualTo(1);
        criteria.andShowFlagEqualTo(1);
        criteria.andMenuIdIn(Arrays.asList(menuIds));
        return null;
    }
}
