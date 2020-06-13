package com.william.bc_mall_server.service.impl;

import com.william.bc_mall_server.mapper.WilliamRolePermissionMapper;
import com.william.bc_mall_server.service.WilliamRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

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

    @Override
    public List<Integer> getPermissionListByRoleId(String roleId) {
        return williamRolePermissionMapper.getPermissionListByRoleId(roleId);
    }
}
