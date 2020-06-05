package com.william.bc_mall_server.service.impl;

import com.william.bc_mall_server.mapper.WilliamRoleMapper;
import com.william.bc_mall_server.service.WilliamRoleService;
import com.william.bcpojo.WilliamRole;
import com.william.bcpojo.WilliamRoleExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xinchuang
 * @version v1.0
 * @date 2020/6/5 18:01
 * @since Copyright(c) 爱睿智健康科技
 */
@Service
public class WilliamRoleServiceImpl implements WilliamRoleService {

    @Autowired
    private WilliamRoleMapper williamRoleMapper;

    @Override
    public WilliamRole getRole(String uid) {

        return williamRoleMapper.selectByExample();
    }
}
