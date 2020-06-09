package com.william.bc_mall_server.service.impl;

import com.william.bc_mall_server.mapper.WilliamPermissionMenuMapper;
import com.william.bc_mall_server.service.WilliamPermissionMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xinchuang
 * @version v1.0
 * @date 2020/6/9 16:56
 * @since Copyright(c) 爱睿智健康科技
 */
@Service
public class WilliamPermissionMenuServiceImpl implements WilliamPermissionMenuService {

    @Autowired
    private WilliamPermissionMenuMapper williamPermissionMenuMapper;

    @Override
    public List<Integer> getMenuListByPermissionId(Integer permissionId) {
        return williamPermissionMenuMapper.getMenuListByPermissionId(permissionId);
    }
}
