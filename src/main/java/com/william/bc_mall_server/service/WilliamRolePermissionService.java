package com.william.bc_mall_server.service;

import java.util.List;
import java.util.Set;

/**
 * @author xinchuang
 * @version v1.0
 * @date 2020/6/9 16:46
 * @since Copyright(c) 爱睿智健康科技
 */
public interface WilliamRolePermissionService {

    List<Integer> getPermissionListByRoleId(String roleId);
}
