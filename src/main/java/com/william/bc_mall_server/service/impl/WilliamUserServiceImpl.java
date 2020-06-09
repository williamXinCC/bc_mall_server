package com.william.bc_mall_server.service.impl;

import com.william.bc_mall_server.mapper.WilliamUserMapper;
import com.william.bc_mall_server.service.WilliamUserService;
import com.william.bcpojo.WilliamUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xinchuang
 * @version v1.0
 * @date 2020/6/9 16:40
 * @since Copyright(c) 爱睿智健康科技
 */
@Service
public class WilliamUserServiceImpl implements WilliamUserService {

    @Autowired
    private WilliamUserMapper williamUserMapper;

    @Override
    public WilliamUser getUserByUid(String uid) {
        return williamUserMapper.selectByPrimaryKey(uid);
    }

    @Override
    public WilliamUser getUserByPhone(String phone) {
        return williamUserMapper.getUserByPhone(phone);
    }
}
