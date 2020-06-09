package com.william.bc_mall_server.service;

import com.william.bcpojo.WilliamUser;

/**
 * @author xinchuang
 * @version v1.0
 * @date 2020/6/5 17:58
 * @since Copyright(c) 爱睿智健康科技
 */
public interface WilliamUserService {

    WilliamUser getUserByUid(String userName);


    WilliamUser getUserByPhone(String phone);
}
