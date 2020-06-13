package com.william.bc_mall_server.controller;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.william.bc_mall_server.redis.RedisService;
import com.william.bc_mall_server.service.WilliamMenuService;
import com.william.bcconstant.BcConsts;
import com.william.bcpojo.ActiverUser;
import com.william.bcpojo.WilliamMenu;
import com.william.bcpojo.WilliamUser;
import com.william.constant.Constant;
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
     * @param williamMenu :
     * @return : com.william.pojo.DataGridView
     */
    @RequestMapping("/getIndexLeftMenuJson")
    public Result  getIndexLeftMenuJson(WilliamMenu williamMenu) {
        String acUser = redisService.getStr("user");
        ActiverUser activerUser = JSONUtil.toBean(acUser, ActiverUser.class);
        List<WilliamMenu> menusList = new ArrayList<>();
        List<TreeNode> treeNodes = new ArrayList<>();
        WilliamUser williamUser = activerUser.getWilliamUser();
        if(williamUser.getUserType() == 2){
            menusList = williamMenuService.getMenusByType(BcConsts.MENU_TYPE);
        }else{
            menusList = williamMenuService.getMenusByIds(activerUser.getMenus(), BcConsts.MENU_TYPE);
        }
        for (WilliamMenu m : menusList) {
            Integer id = m.getMenuId();
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
     * 加载菜单管理左边的菜单树的json
     */
    @RequestMapping("/getMenuManagerLeftTreeJson")
    public Result getMenuManagerLeftTreeJson() {
        List<WilliamMenu> menusByType = williamMenuService.getMenusByType(1);
        List<TreeNode> treeNodes=new ArrayList<>();
        for (WilliamMenu menu : menusByType) {
            Boolean spread = menu.getSpread() == 1;
            treeNodes.add(new TreeNode(menu.getMenuId(), menu.getPid(), menu.getTitle(), spread,menu.getSeq()));
        }
        treeNodes.sort((a,b) -> (a.getSeq() - b.getSeq()));
        return Result.getResult(RespCodeAndMsg.OPERATE_SUCCESS,treeNodes);
    }
}
