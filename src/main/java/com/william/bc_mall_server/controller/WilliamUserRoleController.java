package com.william.bc_mall_server.controller;

import com.william.bc_mall_server.service.WilliamPermissionService;
import com.william.bc_mall_server.service.WilliamRolePermissionService;
import com.william.bc_mall_server.service.WilliamRoleService;
import com.william.bcpojo.WilliamPermission;
import com.william.bcpojo.vo.RoleVo;
import com.william.constant.RespCodeAndMsg;
import com.william.pojo.Result;
import com.william.tree.TreeNode;
import com.william.utils.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


/**
 * @author xinchuang
 * @version v1.0
 * @date 2020/6/13 13:49
 * @since Copyright(c) 爱睿智健康科技
 */
@RestController
@RequestMapping("/william-role")
public class WilliamUserRoleController {

    @Autowired
    private WilliamRoleService williamRoleService;

    @Autowired
    private WilliamPermissionService williamPermissionService;

    @Autowired
    private WilliamRolePermissionService williamRolePermissionService;
    /**
     * 获取所有角色 分页
     * @author     xinchuang
     * @return : com.william.pojo.Result
     */
    @RequestMapping("/getAllRoles")
    public Result getAllRoles(Integer page,Integer limit){
        return williamRoleService.getAllRoles(page,limit);
    }

    /**
     * 添加
     */
    @PostMapping("/addRole")
    public Result addRole(RoleVo roleVo) {
        // 创建人
        roleVo.setCreateTime(new Date());
        williamRoleService.addRole(roleVo);
        return Result.getResult(RespCodeAndMsg.ADD_SUCCESS);
    }


    /**
     * 修改
     */
    @RequestMapping("/updateRole")
    public Result updateRole(RoleVo roleVo) {
        williamRoleService.updateByRoleId(roleVo);
        return Result.getResult(RespCodeAndMsg.UPDATE_SUCCESS);
    }

    /**
     * 删除
     */
    @RequestMapping("/deleteRole")
    public Result deleteRole(Integer roleId) {
        williamRoleService.removeById(roleId);
        return Result.getResult(RespCodeAndMsg.DISPATCH_SUCCESS);
    }


    /**
     * 根据角色ID加载菜单和权限的树的json串
     */
    @RequestMapping("/initPermissionByRoleId")
    public Result initPermissionByRoleId(Integer roleId) {
        //查询所有可用的菜单和权限
        List<WilliamPermission> allPermissions = williamPermissionService.getAllPermissionAndMenus();
        /**
         * 1,根据角色ID查询当前角色拥有的所有的权限或菜单ID
         * 2,根据查询出来的菜单ID查询权限和菜单数据
         */
        List<Integer> currentRolePermissions = this.williamRolePermissionService.getPermissionListByRoleId(roleId);
        List<WilliamPermission> carrentPermissions=null;
        if(currentRolePermissions.size() > 0) { //如果有ID就去查询
            carrentPermissions = williamPermissionService.getPermissionByIds(currentRolePermissions);
        }else {
            carrentPermissions=new ArrayList<>();
        }
        //构造 List<TreeNode>
        List<TreeNode> nodes=new ArrayList<>();
        for (WilliamPermission p1 : allPermissions) {
            String checkArr="0";
            for (WilliamPermission p2 : carrentPermissions) {
                if(Objects.equals(p1.getId(),p2.getId())) {
                    checkArr="1";
                    break;
                }
            }
            Boolean spread= Objects.equals(p1.getSpread(),1);
            nodes.add(new TreeNode(p1.getId(), p1.getPid(), p1.getTitle(), spread, checkArr));
        }
        return new Result(nodes);
    }

    /**
     * 保存角色和菜单权限之间的关系
     */
    @RequestMapping("/saveRolePermission")
    public Result saveRolePermission(String roleId,Integer[] ids) {
        williamRolePermissionService.saveRolePermission(roleId,ids);
        return Result.getResult(RespCodeAndMsg.DISPATCH_SUCCESS);
    }

}
