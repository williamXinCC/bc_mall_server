package com.william.bc_mall_server.service;

import com.william.bcpojo.WilliamPermission;
import com.william.bcpojo.vo.PermissionVo;

import java.util.List;
/**
 * @author xinchuang
 * @version v1.0
 * @date 2020/6/5 17:58
 * @since Copyright(c) 爱睿智健康科技
 */
public interface WilliamMenuService {

    List<WilliamPermission> getMenusByType(Integer type);

    List<WilliamPermission> getMenusByIds(List<Integer> menuList,Integer type);

    void addMenu(WilliamPermission williamPermission);

    void updateMenuById(WilliamPermission williamPermission);

    List<WilliamPermission> getMenuHasChildrenNode(WilliamPermission permission);

    void deleteMenuById(WilliamPermission williamPermission);

    // 条件查询菜单
    List<WilliamPermission> getMenusByCondition(PermissionVo permissionVo);
}
