package com.william.bc_mall_server.service;

import java.util.List;

/**
 * @author xinchuang
 * @version v1.0
 * @date 2020/6/9 16:56
 * @since Copyright(c) 爱睿智健康科技
 */
public interface WilliamPermissionMenuService {

    List<Integer> getMenuListByPermissionId(Integer permissionId);
}
