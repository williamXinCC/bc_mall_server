package com.william.bc_mall_server.service.impl;

import com.william.bc_mall_server.mapper.WilliamUserRoleMapper;
import com.william.bc_mall_server.service.WilliamUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xinchuang
 * @version v1.0
 * @date 2020/6/9 16:28
 * @since Copyright(c) 爱睿智健康科技
 */
@Service
public class WilliamUserRoleServiceImpl implements WilliamUserRoleService {

    @Autowired
    private WilliamUserRoleMapper williamUserRoleMapper;

    @Override
    public List<String> getRoleCodeListByUid(String userId) {
        return williamUserRoleMapper.getRoleCodeListByUid(userId);
    }
}
