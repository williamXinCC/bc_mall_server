package com.william.bc_mall_server.service.impl;

import com.william.bc_mall_server.mapper.WilliamPermissionMapper;
import com.william.bc_mall_server.mapper.WilliamPermissionMapper;
import com.william.bc_mall_server.service.WilliamMenuService;
import com.william.bc_mall_server.service.WilliamPermissionService;
import com.william.bcconstant.BcConsts;
import com.william.bcpojo.WilliamPermission;
import com.william.bcpojo.WilliamPermissionExample;
import com.william.bcpojo.WilliamPermission;
import com.william.bcpojo.WilliamPermissionExample;
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
    private WilliamPermissionMapper williamPermissionMapper;

    /**
     * 查所有
     * @author     xinchuang
     * @param type :
     * @return : java.util.List<com.william.bcpojo.WilliamPermission>
     */
    @Override
    public List<WilliamPermission> getMenusByType(Integer type) {
        WilliamPermissionExample williamPermissionExample = new WilliamPermissionExample();
        WilliamPermissionExample.Criteria criteria = williamPermissionExample.createCriteria();
        criteria.andTypeEqualTo(type);
        criteria.andStatusEqualTo(1);
        criteria.andShowFlagEqualTo(1);
//        criteria.andTenantIdEqualTo(BcConsts.TENANT_ID);
        williamPermissionExample.setOrderByClause("seq asc");
        return williamPermissionMapper.selectByExample(williamPermissionExample);
    }

    /**
     * ids查询菜单
     * @author     xinchuang
     * @param menuList :
     * @param type :
     * @return : java.util.List<com.william.bcpojo.WilliamPermission>
     */
    @Override
    public List<WilliamPermission> getMenusByIds(List<Integer> menuList,Integer type) {
        WilliamPermissionExample williamMenuExample = new WilliamPermissionExample();
        WilliamPermissionExample.Criteria criteria = williamMenuExample.createCriteria();
        if(0 != type){
            criteria.andTypeEqualTo(type);
        }
        criteria.andStatusEqualTo(1);
        criteria.andShowFlagEqualTo(1);
        criteria.andTenantIdEqualTo(BcConsts.TENANT_ID);
        criteria.andIdIn(menuList);
        williamMenuExample.setOrderByClause("seq asc");
        return williamPermissionMapper.selectByExample(williamMenuExample);
    }


    /**
     * 添加菜单
     * @author     xinchuang
     * @param williamPermission :
     * @return : void
     */
    @Override
    public void addMenu(WilliamPermission williamPermission) {
        williamPermission.setType(BcConsts.PERMISSION_TYPE_MENU);
        williamPermissionMapper.insertSelective(williamPermission);
    }

    @Override
    public void updateMenuById(WilliamPermission williamPermission) {
        williamPermissionMapper.updateByPrimaryKeySelective(williamPermission);
    }


    /**
     * 查询当前菜单下是否有子菜单
     * @author     xinchuang
     * @param permission :
     * @return : java.util.List<com.william.bcpojo.WilliamPermission>
     */
    @Override
    public List<WilliamPermission> getMenuHasChildrenNode(WilliamPermission permission) {
        return williamPermissionMapper.selectMenusByPid(permission.getPid());
    }

    /**
     * 删除菜单
     * @author     xinchuang
     * @param williamPermission :
     * @return : void
     */
    @Override
    public void deleteMenuById(WilliamPermission williamPermission) {
        williamPermission.setStatus(99);
        williamPermissionMapper.updateByPrimaryKeySelective(williamPermission);
    }

}
