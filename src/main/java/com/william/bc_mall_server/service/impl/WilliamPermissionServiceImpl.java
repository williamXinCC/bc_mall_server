package com.william.bc_mall_server.service.impl;

import com.william.bc_mall_server.mapper.WilliamPermissionMapper;
import com.william.bc_mall_server.service.WilliamPermissionService;
import com.william.bcpojo.WilliamPermission;
import com.william.bcpojo.vo.PermissionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author xinchuang
 * @version v1.0
 * @date 2020/6/15 11:42
 * @since Copyright(c) 爱睿智健康科技
 */
@Service
public class WilliamPermissionServiceImpl implements WilliamPermissionService {

    @Autowired
    private WilliamPermissionMapper williamPermissionMapper;

    /**
     * 所有权限 + 菜单
     * @author     xinchuang
     * @return : java.util.List<com.william.bcpojo.WilliamPermission>
     */
    @Override
    public List<WilliamPermission> getAllPermissionAndMenus() {
        return williamPermissionMapper.getAllPermissionAndMenus();
    }


    /**
     * 查询当前菜单下所有权限
     * @author     xinchuang
     * @return : java.util.List<com.william.bcpojo.WilliamPermission>
     * @param id
     */
    @Override
    public List<WilliamPermission> getAllPermission(Integer id) {
        return williamPermissionMapper.getAllPermission(id);
    }


    /**
     * 角色 id查询权限
     * @author     xinchuang
     * @param currentRolePermissions :
     * @return : java.util.List<com.william.bcpojo.WilliamPermission>
     */
    @Override
    public List<WilliamPermission> getPermissionByIds(List<Integer> currentRolePermissions) {
        return null;
    }

    @Override
    public void save(PermissionVo permissionVo) {
        williamPermissionMapper.insertSelective(permissionVo);
    }

    @Override
    public void updateById(PermissionVo permissionVo) {
        williamPermissionMapper.updateByPrimaryKeySelective(permissionVo);
    }

    @Override
    public void deletePermissionById(Integer id) {
        WilliamPermission williamPermission = new WilliamPermission();
        williamPermission.setId(id);
        williamPermission.setStatus(99);
        williamPermission.setModTime(new Date());
        williamPermissionMapper.updateByPrimaryKeySelective(williamPermission);
    }
}
