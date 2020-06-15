package com.william.bc_mall_server.service;


import com.william.bcpojo.WilliamPermission;
import com.william.bcpojo.vo.PermissionVo;

import java.util.List;

/**
 * @author xinchuang
 * @version v1.0
 * @date 2020/6/5 17:59
 * @since Copyright(c) 爱睿智健康科技
 */
public interface WilliamPermissionService {

    List<WilliamPermission> getAllPermissionAndMenus();

    List<WilliamPermission> getAllPermission(Integer id);

    List<WilliamPermission> getPermissionByIds(List<Integer> currentRolePermissions);

    void save(PermissionVo permissionVo);

    void updateById(PermissionVo permissionVo);

    void deletePermissionById(Integer id);
}
