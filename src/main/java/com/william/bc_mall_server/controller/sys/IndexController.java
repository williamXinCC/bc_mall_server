package com.william.bc_mall_server.controller.sys;

import com.william.bc_mall_server.service.WilliamUserService;
import com.william.bcpojo.WilliamUser;
import com.william.constant.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author xinchuang
 * @version v1.0
 * @date 2019/11/14 16:33
 * @since Copyright(c) 爱睿智健康科技
 */
@Controller
@RequestMapping(value = "/index")
public class IndexController {

    @Autowired
    private WilliamUserService userService;
//
//    @Autowired
//    private IWilliamUserRoleService userRoleService;
//
//    @Autowired
//    private IWilliamSysNoticeService noticeService;
//
//    // 优惠券
//
//    // 优惠券配置
//    @RequestMapping(value = "/toCoupon")
//    public String toCoupon(){return Constant.TO_PAGE + "coupon/coupon";}
//
//    // 优惠券记录
//    @RequestMapping(value = "/toCouponHistory")
//    public String toCouponHistory(){return Constant.TO_PAGE + "coupon/couponHistory";}
//
//    // 分销
//    @RequestMapping(value = "/toDvipManager")
//    public String toDvipManager(){return Constant.TO_PAGE + "distribution/dvip/dvipManager";}
//
//    @RequestMapping(value = "/toDvipRight")
//    public String toDvipRight(){return Constant.TO_PAGE + "distribution/dvip/dvipRight";}
//
//    @RequestMapping(value = "/toDvipLeft")
//    public String toDvipLeft(){return Constant.TO_PAGE + "distribution/dvip/dvipLeft";}
//
//    @RequestMapping(value = "/toDconfig")
//    public String toDconfig(){return Constant.TO_PAGE + "distribution/dconfig/dconfig";}
//
//    @RequestMapping(value = "/toDrecord")
//    public String toDrecord(){return Constant.TO_PAGE + "distribution/drecord/drecord";}
//
//    // 幸运转盘
//    @RequestMapping(value = "/toLuckdrawConfigManager")
//    public String toLuckdrawconfigManager(){return Constant.TO_PAGE + "luckdraw/luckdrawConfigManager";}
//
//    @RequestMapping(value = "/toLuckdrawConfigRight")
//    public String toLuckdrawconfigRight(){return Constant.TO_PAGE + "luckdraw/luckdrawConfigRight";}
//
//    @RequestMapping(value = "/toLuckdrawConfigLeft")
//    public String toLuckdrawconfigLeft(){return Constant.TO_PAGE + "luckdraw/luckdrawConfigLeft";}
//
//    @RequestMapping(value = "/toLuckdrawRecord")
//    public String toLuckdrawRecord(){return Constant.TO_PAGE + "luckdraw/luckdrawRecord";}
//    // 秒杀 =============
//    // 秒杀活动
//    @RequestMapping(value = "/toSeckillactive")
//    public String toSeckillactive(){return Constant.TO_PAGE + "seckill/seckillactive/seckillactive";}
//
//    // 秒杀商品配置
//    @RequestMapping(value = "/toSeckillGoodsManager")
//    public String toSeckillGoodsManager(){return Constant.TO_PAGE + "seckill/goods/seckillGoodsManager";}
//
//    @RequestMapping(value = "/toSeckillGoodsLeft")
//    public String toSeckillGoodsLeft(){return Constant.TO_PAGE + "seckill/goods/seckillGoodsLeft";}
//
//    @RequestMapping(value = "/toSeckillGoodsRight")
//    public String toSeckillGoodsRight(){return Constant.TO_PAGE + "seckill/goods/seckillGoodsRight";}
//
//    // 登录日志
//    @RequestMapping(value = "/toLoginLogManager")
//    public String toLoginLogManager(){return Constant.TO_SYSTEM + "loginlog/loginLogManager";}
//
//    // 支付管理
//    @RequestMapping(value = "/toPayManager")
//    public String toPayManager(){return Constant.TO_PAGE + "pay/payManager";}
//
//    @RequestMapping(value = "/toPayChannelManager")
//    public String toPayChannelManager(){return Constant.TO_PAGE + "pay/payChannel";}
//
//    // 订单管理
//    @RequestMapping(value = "/toOrderManager")
//    public String toOrderManager(){return Constant.TO_PAGE + "order/orderManager";}
//
//    // 客户管理页面
//    @RequestMapping(value = "/toCustomerManager")
//    public String toCustomerManager(){return Constant.TO_PAGE + "customer/customerManager";}

//
//    // 部门管理
//    @RequestMapping(value = "/toDept")
//    public String toDept(){
//        return Constant.TO_SYSTEM + "dept/deptManager";
//    }
//
//    @RequestMapping(value = "/toDeptLeft")
//    public String toDeptLeft(){return Constant.TO_SYSTEM + "dept/deptLeft";}
//
//    @RequestMapping(value = "/toDeptRight")
//    public String toDeptRight(){return Constant.TO_SYSTEM + "dept/deptRight";}
//
//

//
//
////    @RequestMapping(value = "/toMenuEditor")
////    public String toMenuEditor(@RequestParam(value = "pid",required = false) String pid,Model model){
////        QueryWrapper<WilliamMenu> williamMenuQueryWrapper = new QueryWrapper<>();
////        williamMenuQueryWrapper.eq("root_id","/");
////        List<WilliamMenu> list = iWilliamMenuService.list(williamMenuQueryWrapper);
////        model.addAttribute("menuList",list);
////        return Constant.TO_SYSTEM + "menu/menuEditor";
////    }
////
////
////    @RequestMapping(value = "/toMenuList")
////    public String toMenuList(Model model){
////        List<WilliamMenu> menuList = iWilliamMenuService.list();
////        model.addAttribute("menuList",menuList);
////        return Constant.TO_SYSTEM + "menu/menuList";}
//
//

    // 商品
    @RequestMapping(value = "/toGoodsLeft")
    public String toGoodsLeft(){return Constant.TO_PAGE + "goods/goodsLeft";}

    @RequestMapping(value = "/toGoodsRight")
    public String toGoodsRight(){return Constant.TO_PAGE + "goods/goodsRight";}

    @RequestMapping(value = "/toGoodsManager")
    public String toGoods(){
        return Constant.TO_PAGE + "goods/goodsManager";
    }

    // 商品分类
    @RequestMapping(value = "/toGoodsCategoryManager")
    public String toGoodsCategoryManager(){
        return Constant.TO_PAGE + "goods/goodsCategory/goodsCategoryManager";
    }

    @RequestMapping(value = "/toGoodsCategoryRight")
    public String toGoodsCategoryRight(){
        return Constant.TO_PAGE + "goods/goodsCategory/goodsCategoryRight";
    }

    @RequestMapping(value = "/toGoodsCategoryLeft")
    public String toGoodsCategoryLeft(){
        return Constant.TO_PAGE + "goods/goodsCategory/goodsCategoryLeft";
    }



    // 系统管理

    @RequestMapping(value = "/toSystemParameter")
    public String toSystemParameter(){
        return Constant.TO_PAGE + "systemParameter/systemParameter";
    }


    @RequestMapping(value = "/toNewsList")
    public String toNewsList(){
        return Constant.TO_PAGE + "news/newsList";
    }


    @RequestMapping(value = "/toNewsAdd")
    public String toNewsAdd(){
        return Constant.TO_PAGE + "news/newsAdd";
    }


    @RequestMapping(value = "/toLinksList")
    public String toLinkList(){
        return Constant.TO_PAGE + "links/linksList";
    }

    @RequestMapping(value = "/toLinksAdd")
    public String toLinksAdd(){
        return Constant.TO_PAGE + "links/linksAdd";
    }

    @RequestMapping(value = "/toMessageReply")
    public String toMessageReply(){
        return Constant.TO_PAGE + "message/messageReply";
    }

    @RequestMapping(value = "/toMessage")
    public String toMessage(){
        return Constant.TO_PAGE + "message/message";
    }

    @RequestMapping(value = "/toImages")
    public String toImages(){
        return Constant.TO_PAGE + "img/images";
    }


    // 菜单管理

    /**
     * 跳转到菜单管理
     *
     */
    @RequestMapping("/toMenuManager")
    public String toMenuManager() {
        return Constant.TO_SYSTEM + "menu/menuManager";
    }

    /**
     * 跳转到菜单管理-left
     *
     */
    @RequestMapping("/toMenuLeft")
    public String toMenuLeft() {
        return Constant.TO_SYSTEM + "menu/menuLeft";
    }


    /**
     * 跳转到菜单管理--right
     *
     */
    @RequestMapping("/toMenuRight")
    public String toMenuRight() {
        return Constant.TO_SYSTEM + "menu/menuRight";
    }

    /**
     * 角色 权限 管理
     * @author     xinchuang
     * @return : java.lang.String
     */
    @RequestMapping(value = "/toRole")
    public String toRole(){
        return Constant.TO_SYSTEM + "role/roleManager";
    }

    // 权限
    @RequestMapping(value = "/toPermissionLeft")
    public String toPermissionLeft(){
        return Constant.TO_SYSTEM + "permission/permissionLeft";
    }

    @RequestMapping(value = "/toPermissionRight")
    public String toPermissionRight(){
        return Constant.TO_SYSTEM + "permission/permissionRight";
    }

    @RequestMapping(value = "/toPermissionManager")
    public String toPermissionManager(){
        return Constant.TO_SYSTEM + "permission/permissionManager";
    }


    /**
     * 用户管理
     * @author     xinchuang
     * @param model :
     * @return : java.lang.String
     */
    @RequestMapping(value = "/toUserManager")
    public String toUserManager(Model model){
        return Constant.TO_SYSTEM + "user/userManager";
    }

    @RequestMapping(value = "/toUserInfo")
    public String toUserInfo(){
        return Constant.TO_SYSTEM + "user/userInfo";
    }

    @Deprecated
    @RequestMapping(value = "/toAllUsers")
    public String toAllUsers(){
        return Constant.TO_SYSTEM + "user/allUsers";
    }

    @Deprecated
    @RequestMapping(value = "/toAddUser")
    public String toAddUser(){
        return Constant.TO_PAGE + "user/addUser";
    }

    // 登录
    @RequestMapping(value = "/toLogin")
    public String toLogin(){ return Constant.TO_SYSTEM + "index/login"; }

    // 主页
    @RequestMapping(value = "/toMain")
    public String toMain(){
        return Constant.TO_SYSTEM + "index/main";
    }

    // index页面
    @RequestMapping(value = "/toIndex")
    public String toIndex(Model model){
//        List<WilliamSysNotice> sysNotices = noticeService.getSysNotices();
//        model.addAttribute("notic",sysNotices.get(0));
        return Constant.TO_SYSTEM + "index/index"; }

    // 修改密码页面
    @RequestMapping(value = "/toChangePwd")
    public String toChangePwd(){
        return Constant.TO_SYSTEM + "user/changePwd";
    }

    // 404页面
    @RequestMapping(value = "/to404")
    public String to404(){
        return Constant.TO_SYSTEM + "index/404";
    }

    // 菜单图标
    @RequestMapping(value = "/toIcon")
    public String toIcon(String id,Model model){
        model.addAttribute("id",id);
        return Constant.TO_SYSTEM + "menu/icon";
    }
}
