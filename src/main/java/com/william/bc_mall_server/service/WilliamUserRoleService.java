package com.william.bc_mall_server.service;

import java.util.List;

/**
 * @author xinchuang
 * @version v1.0
 * @date 2020/6/9 16:28
 * @since Copyright(c) 爱睿智健康科技
 */
public interface WilliamUserRoleService {

    List<String> getRoleListByUid(String userId);
}
