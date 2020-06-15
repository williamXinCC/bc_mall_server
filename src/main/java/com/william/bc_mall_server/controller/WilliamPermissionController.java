package com.william.bc_mall_server.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.william.bc_mall_server.service.WilliamMenuService;
import com.william.bc_mall_server.service.WilliamPermissionService;
import com.william.bcconstant.BcConsts;
import com.william.bcpojo.WilliamPermission;
import com.william.bcpojo.vo.PermissionVo;
import com.william.constant.Constant;
import com.william.constant.RespCodeAndMsg;
import com.william.pojo.Result;
import com.william.tree.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xinchuang
 * @version v1.0
 * @date 2020/6/15 10:50
 * @since Copyright(c) 爱睿智健康科技
 */
@RestController
@RequestMapping("/william-permission")
public class WilliamPermissionController {

    @Autowired
    private WilliamPermissionService permissionService;

    @Autowired
    private WilliamMenuService williamMenuService;

    /**
     * 加载权限管理左边的权限树的json
     */
    @RequestMapping("/loadPermissionManagerLeftTreeJson")
    public Result loadPermissionManagerLeftTreeJson(PermissionVo permissionVo) {
        List<WilliamPermission> menusByType = williamMenuService.getMenusByType(BcConsts.PERMISSION_TYPE_MENU);
        List<TreeNode> treeNodes = new ArrayList<>();
        for (WilliamPermission permission : menusByType) {
            Boolean spread = permission.getSpread() == 1 ? true : false;
            treeNodes.add(new TreeNode(permission.getId(), permission.getPid(), permission.getTitle(), spread));
        }
        return new Result(treeNodes);
    }

    /**
     * 查询当前菜单下所有权限
     */
    @RequestMapping("/loadAllPermission")
    public Result loadAllPermission(PermissionVo permissionVo) {
        PageHelper.startPage(permissionVo.getPage(),permissionVo.getLimit());
        List<WilliamPermission> allPermission = permissionService.getAllPermission(permissionVo.getId());
        PageInfo<WilliamPermission> pageInfo = new PageInfo<>(allPermission);
        return new Result(pageInfo.getTotal(),allPermission);
    }


    /**
     * 加载权限管理左边的菜单树的json
     */
    @RequestMapping("loadMenuManagerLeftTreeJson")
    public Result loadMenuManagerLeftTreeJson(PermissionVo permissionVo) {
        List<WilliamPermission> menusByType = williamMenuService.getMenusByType(BcConsts.PERMISSION_TYPE_MENU);
        List<TreeNode> treeNodes = new ArrayList<>();
        for (WilliamPermission permission : menusByType) {
            Boolean spread = permission.getSpread() == 1 ? true : false;
            treeNodes.add(new TreeNode(permission.getId(), permission.getPid(), permission.getTitle(), spread));
        }
        return new Result(treeNodes);
    }


    /**
     * 添加
     *
     * @param permissionVo
     * @return
     */
    @RequestMapping("addPermission")
    public Result addPermission(PermissionVo permissionVo) {
        // 设置添加类型
        permissionVo.setType(BcConsts.PERMISSION_TYPE_BUTTON);
        permissionService.save(permissionVo);
        return Result.getResult(RespCodeAndMsg.OPERATE_SUCCESS);
    }

    /**
     * 修改
     * @param permissionVo
     * @return
     */
    @RequestMapping("updatePermission")
    public Result updatePermission(PermissionVo permissionVo) {
        permissionService.updateById(permissionVo);
        return Result.getResult(RespCodeAndMsg.OPERATE_SUCCESS);
    }

    /**
     * 删除
     *
     * @param permissionVo
     * @return
     */
    @RequestMapping("deletePermissionById")
    public Result deletePermissionById(PermissionVo permissionVo) {
        permissionService.deletePermissionById(permissionVo.getId());
        return Result.getResult(RespCodeAndMsg.OPERATE_SUCCESS);
    }
}
