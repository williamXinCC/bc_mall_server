package com.william.bc_mall_server.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.william.bc_mall_server.service.WilliamRoleService;
import com.william.bcpojo.vo.RoleVo;
import com.william.constant.Constant;
import com.william.constant.RespCodeAndMsg;
import com.william.pojo.Result;
import com.william.utils.ObjectId;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
        String s = ObjectId.get();
        roleVo.setRoleCode(s);
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
//
//    /**
//     * 删除
//     */
//    @RequestMapping("/deleteRole")
//    public Result deleteRole(Integer id) {
//        this.iWilliamRoleService.removeById(id);
//        return Result.getResult(RespCodeAndMsg.DISPATCH_SUCCESS);
//    }
//
//
//    /**
//     * 根据角色ID加载菜单和权限的树的json串
//     */
//    @RequestMapping("/initPermissionByRoleId")
//    public DataGridView initPermissionByRoleId(String roleId) {
//        //查询所有可用的菜单和权限
//        QueryWrapper<WilliamMenu> queryWrapper=new QueryWrapper<>();
//        queryWrapper.eq("status", Constant.AVAILABLE_TRUE);
//        List<WilliamMenu> allPermissions = iWilliamMenuService.list(queryWrapper);
//        /**
//         * 1,根据角色ID查询当前角色拥有的所有的权限或菜单ID
//         * 2,根据查询出来的菜单ID查询权限和菜单数据
//         */
//        List<Integer> currentRolePermissions=this.iWilliamMenuService.queryRolePermissionIdsByRid(roleId);
//        List<WilliamMenu> carrentPermissions=null;
//        if(currentRolePermissions.size()>0) { //如果有ID就去查询
//            queryWrapper.in("id", currentRolePermissions);
//            carrentPermissions = iWilliamMenuService.list(queryWrapper);
//        }else {
//            carrentPermissions=new ArrayList<>();
//        }
//        //构造 List<TreeNode>
//        List<TreeNode> nodes=new ArrayList<>();
//        for (WilliamMenu p1 : allPermissions) {
//            String checkArr="0";
//            for (WilliamMenu p2 : carrentPermissions) {
//                if(Objects.equals(p1.getId(),p2.getId())) {
//                    checkArr="1";
//                    break;
//                }
//            }
//            Boolean spread=Objects.equals(p1.getSpread(),1);
//            nodes.add(new TreeNode(p1.getId(), p1.getPid(), p1.getTitle(), spread, checkArr));
//        }
//        return new DataGridView(nodes);
//    }
//
//    /**
//     * 保存角色和菜单权限之间的关系
//     */
//    @RequestMapping("/saveRolePermission")
//    public Result saveRolePermission(String rid,Integer[] ids) {
//        this.iWilliamRoleService.saveRolePermission(rid,ids);
//        return Result.getResult(RespCodeAndMsg.DISPATCH_SUCCESS);
//    }

}
