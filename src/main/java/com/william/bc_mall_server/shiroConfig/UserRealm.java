package com.william.bc_mall_server.shiroConfig;

import com.william.bc_mall_server.service.*;
import com.william.bcpojo.ActiverUser;
import com.william.bcpojo.WilliamMenu;
import com.william.bcpojo.WilliamUser;
import com.william.utils.CollectionUtil;
import com.william.utils.IdSaltUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.*;

@Configuration
public class UserRealm extends AuthorizingRealm{

    @Autowired
//    @Lazy
    private WilliamUserService userService;

    @Autowired
//    @Lazy
    private WilliamMenuService menuService;

    @Autowired
//    @Lazy
    private WilliamRoleService roleService;

    @Autowired
    private WilliamUserRoleService userRoleService;

    @Autowired
    private WilliamRolePermissionService williamRolePermissionService;

    @Autowired
    private WilliamPermissionMenuService williamPermissionMenuService;

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    /**
     * 授权
     * @author     xinchuang
     * @param principals :
     * @return : org.apache.shiro.authz.AuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
//        ActiverUser activerUser = (ActiverUser) principals.getPrimaryPrincipal();
//        WilliamUser user = activerUser.getWilliamUser();
//        // 获取用户角色列表
//        List<String> roleList = williamUserRoleMapper.getRoleListByUid(user.getUserId());
//        // 获取权限
//        List<Integer> permissions = activerUser.getPermissions();
//        // 顶级管理员
//        if(Objects.equals(role.ge, Constant.USER_TYPE_SUPER)) {
//            authorizationInfo.addStringPermission("*:*");
//        }else {
//            if(Objects.nonNull(permissions) && permissions.size()>0) {
//                authorizationInfo.addStringPermissions(permissions);
//            }
//        }
        return authorizationInfo;
    }

    /**
     * 认证
     * @author     xinchuang
     * @param token :
     * @return : org.apache.shiro.authc.AuthenticationInfo
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userPhone = token.getPrincipal().toString();
        // 查询用户
        WilliamUser williamUser = userService.getUserByPhone(userPhone);
        if(Objects.nonNull(williamUser)){
            ActiverUser activerUser = new ActiverUser();
            activerUser.setWilliamUser(williamUser);
            // uid + 角色 + 权限查询
            String uid = williamUser.getUserId();
            // 查询角色
            List<String> currentRoleIds = userRoleService.getRoleListByUid(uid);
            // 根据角色查询权限
            Set<Integer> permissionsList = new HashSet<>();
            for (String currentRoleId : currentRoleIds) {
                List<Integer> userPermissions = williamRolePermissionService.getPermissionListByRoleId(currentRoleId);
                if(cn.hutool.core.collection.CollectionUtil.isNotEmpty(userPermissions)){
                    permissionsList.addAll(userPermissions);
                }
            }
            List<Integer> permissions = new ArrayList<>(permissionsList);
            // 权限 查询菜单
            Set<Integer> menuList = new HashSet<>();
            if(permissionsList.size() > 0){
                for (Integer permissionId : permissionsList) {
                   List<Integer> menuIds = williamPermissionMenuService.getMenuListByPermissionId(permissionId);
                   if(cn.hutool.core.collection.CollectionUtil.isNotEmpty(menuIds)){
                       menuList.addAll(menuIds);
                   }
                }
            }
            List<Integer> menus = new ArrayList<>(menuList);
//            List<WilliamMenu> list = new ArrayList<>();
//            // 根据角色ID查询权限
//            if(menuList.size() > 0){
//                // 查询菜单
//                list = menuService.getMenusByIds(menuList,1);
//            }
            // 用户权限
            activerUser.setPermissions(permissions);
            // 用户角色
            activerUser.setRoles(currentRoleIds);
            // 用户菜单
            activerUser.setMenus(menus);
            // 手机号 + 账号ID 为盐
            ByteSource credentialsSalt = IdSaltUtils.getSalt(williamUser.getUserPhone(), williamUser.getUserId());
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(activerUser, williamUser.getUserPassword(), credentialsSalt, getName());
            Session session = SecurityUtils.getSubject().getSession();
            session.setAttribute("userSession",activerUser);
            return authenticationInfo;
        }
        return null;
    }

}
