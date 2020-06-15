package com.william.bc_mall_server.controller;

import cn.hutool.json.JSONUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.william.bc_mall_server.redis.RedisService;
import com.william.bc_mall_server.service.WilliamMenuService;
import com.william.bcconstant.BcConsts;
import com.william.bcpojo.ActiverUser;
import com.william.bcpojo.WilliamPermission;
import com.william.bcpojo.WilliamUser;
import com.william.bcpojo.vo.PermissionVo;
import com.william.constant.RespCodeAndMsg;
import com.william.pojo.Result;
import com.william.tree.TreeNode;
import com.william.tree.TreeNodeBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author xinchuang
 * @version v1.0
 * @date 2020/6/11 10:09
 * @since Copyright(c) 爱睿智健康科技
 */
@RestController
@RequestMapping("/william-menu")
public class WilliamMenuController {

    @Autowired
    private RedisService redisService;

    @Autowired
    private WilliamMenuService williamMenuService;


    /**
     * 左侧菜单  -- 线上需 修改
     * @author     xinchuang
     * @param williamPermission :
     * @return : com.william.pojo.DataGridView
     */
    @RequestMapping("/getIndexLeftMenuJson")
    public Result  getIndexLeftMenuJson(WilliamPermission williamPermission) {
        String acUser = redisService.getStr("user");
        ActiverUser activerUser = JSONUtil.toBean(acUser, ActiverUser.class);
        List<WilliamPermission> menusList = new ArrayList<>();
        List<TreeNode> treeNodes = new ArrayList<>();
        WilliamUser williamUser = activerUser.getWilliamUser();
        if(williamUser.getUserType() == 2){
            menusList = williamMenuService.getMenusByType(BcConsts.PERMISSION_TYPE_MENU);
        }else{
            menusList = activerUser.getMenus();
        }
        for (WilliamPermission m : menusList) {
            Integer id = m.getId();
            Integer pid = m.getPid();
            String title = m.getTitle();
            String icon = m.getIcon();
            String href = m.getHref();
            Integer seq = m.getSeq();
            Boolean spread = m.getSpread() == 1;
            treeNodes.add(new TreeNode(id, pid, title, icon, href, spread,seq));
        }
        //构造层级关系
        List<TreeNode> listResult = TreeNodeBuilder.build(treeNodes, 0);
        listResult.sort((a,b) -> (a.getSeq() - b.getSeq()));
        Result result = new Result();
        result.setData(listResult);
        result.setCode(200);
        return result;
    }


    /**
     * 加载左边的菜单树的json
     */
    @RequestMapping("/getMenuManagerLeftTreeJson")
    public Result getMenuManagerLeftTreeJson() {
        List<WilliamPermission> menusByType = williamMenuService.getMenusByType(BcConsts.PERMISSION_TYPE_MENU);
        List<TreeNode> treeNodes = new ArrayList<>();
        for (WilliamPermission permission : menusByType) {
            Boolean spread = permission.getSpread() == 1 ? true : false;
            treeNodes.add(new TreeNode(permission.getId(), permission.getPid(), permission.getTitle(), spread));
        }
//        treeNodes.sort((a,b) -> (a.getSeq() - b.getSeq()));
        return new Result(treeNodes);
    }


    /**
     * 查询所有菜单
     * @author     xinchuang
     * @param permissionVo :
     * @return : com.william.pojo.DataGridView
     */
    @RequestMapping("/getAllMenu")
    public Result getAllMenu(PermissionVo permissionVo) {
        PageHelper.startPage(permissionVo.getPage(),permissionVo.getLimit());
        List<WilliamPermission> menusByType = williamMenuService.getMenusByType(BcConsts.PERMISSION_TYPE_MENU);
        PageInfo<WilliamPermission> pageInfo = new PageInfo<>(menusByType);
        return new Result(pageInfo.getTotal(),menusByType);
    }

//    /**
//     * 加载最大的排序码
//     * @return
//     */
//    @RequestMapping("/getMenuMaxOrderNum")
//    public Map<String,Object> getMenuMaxOrderNum(){
//        Map<String, Object> map=new HashMap<String, Object>();
//        QueryWrapper<WilliamMenu> queryWrapper=new QueryWrapper<>();
//        queryWrapper.orderByDesc("seq");
//        IPage<WilliamMenu> page=new Page<>(1, 1);
//        List<WilliamMenu> list = this.menuService.page(page, queryWrapper).getRecords();
//        if(list.size()>0) {
//            map.put("value", list.get(0).getSeq() + 1);
//        }else {
//            map.put("value", 1);
//        }
//        return map;
//    }


    /**
     * 添加菜单
     * @author     xinchuang
     * @param williamPermission :
     * @return : com.william.pojo.Result
     */
    @RequestMapping("/addMenu")
    public Result addMenu(WilliamPermission williamPermission) {
        //设置添加类型
        williamMenuService.addMenu(williamPermission);
        return Result.getResult(RespCodeAndMsg.ADD_SUCCESS);
    }


    /**
     * 修改菜单
     * @author     xinchuang
     * @param williamPermission :
     * @return : com.william.pojo.Result
     */
    @RequestMapping("/updateMenuById")
    public Result updateMenuById(WilliamPermission williamPermission) {
        williamMenuService.updateMenuById(williamPermission);
        return Result.getResult(RespCodeAndMsg.UPDATE_SUCCESS);
    }

    /**
     * 查询当前的ID的菜单有没有子菜单
     */
    @RequestMapping("/getMenuHasChildrenNode")
    public Map<String,Object> getMenuHasChildrenNode(WilliamPermission permission){
        Map<String, Object> map=new HashMap<String, Object>();
        List<WilliamPermission> childList = williamMenuService.getMenuHasChildrenNode(permission);
        if(childList.size()>0) {
            map.put("value", true);
        }else {
            map.put("value", false);
        }
        return map;
    }

    /**
     * 删除菜单
     * @author     xinchuang
     * @param williamPermission :
     * @return : com.william.pojo.Result
     */
    @RequestMapping("/deleteMenuById")
    public Result deleteMenuById(WilliamPermission williamPermission) {
        williamMenuService.deleteMenuById(williamPermission);
        return Result.getResult(RespCodeAndMsg.UPDATE_SUCCESS);
    }
}
