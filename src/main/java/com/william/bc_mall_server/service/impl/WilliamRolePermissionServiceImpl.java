package com.william.bc_mall_server.service.impl;

import com.william.bc_mall_server.mapper.WilliamRolePermissionMapper;
import com.william.bc_mall_server.service.WilliamRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author xinchuang
 * @version v1.0
 * @date 2020/6/9 16:47
 * @since Copyright(c) 爱睿智健康科技
 */
@Service
public class WilliamRolePermissionServiceImpl implements WilliamRolePermissionService {

    @Autowired
    private WilliamRolePermissionMapper williamRolePermissionMapper;

    /**
     * 角色id查询权限
     * @author     xinchuang
     * @param roleId :
     * @return : java.util.List<java.lang.Integer>
     */

    @Override
    public List<Integer> getPermissionListByRoleId(Integer roleId) {
        return williamRolePermissionMapper.getPermissionListByRoleId(roleId);
    }

    /**
     * 保存角色和权限
     * @author     xinchuang
     * @param roleId :
     * @param ids :
     * @return : void
     */
    @Override
    public void saveRolePermission(String roleId, Integer[] ids) {
        williamRolePermissionMapper.inserBatchRolePermission(roleId,new Date(),ids);
    }
}
