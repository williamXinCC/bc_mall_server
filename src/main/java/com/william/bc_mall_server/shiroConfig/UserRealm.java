package com.william.bc_mall_server.shiroConfig;

import com.william.bc_mall_server.mapper.WilliamPermissionMapper;
import com.william.bc_mall_server.service.*;
import com.william.bcconstant.BcConsts;
import com.william.bcpojo.ActiverUser;
import com.william.bcpojo.WilliamPermission;
import com.william.bcpojo.WilliamPermissionExample;
import com.william.bcpojo.WilliamUser;
import com.william.constant.Constant;
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
    @Lazy
    private WilliamUserService userService;

    @Autowired
    @Lazy
    private WilliamPermissionMapper permissionMapper;

    @Autowired
    private WilliamUserRoleService userRoleService;

    @Autowired
    private WilliamRolePermissionService williamRolePermissionService;

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
        ActiverUser activerUser = (ActiverUser) principals.getPrimaryPrincipal();
        WilliamUser user = activerUser.getWilliamUser();
        // 获取用户角色列表
//        List<String> roleList = williamUserRoleMapper.getRoleListByUid(user.getUserId());
        // 获取权限
        List<String> permissions = activerUser.getPermissions();
        // 顶级管理员
        if(Objects.equals(user.getUserType(), BcConsts.USER_TYPE_SUPER)) {
            authorizationInfo.addStringPermission("*:*");
        }else if(cn.hutool.core.collection.CollectionUtil.isNotEmpty(permissions)){
            authorizationInfo.addStringPermissions(permissions);
        }
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
            String uid = williamUser.getUserId();
            // 查询角色
            List<Integer> currentRoleIds = userRoleService.getRoleCodeListByUid(uid);
            // 角色查询权限
            Set<Integer> userPermissionsIds = new HashSet<>();
            for (Integer currentRoleId : currentRoleIds) {
                List<Integer> userPermissions = williamRolePermissionService.getPermissionListByRoleId(currentRoleId);
                if(cn.hutool.core.collection.CollectionUtil.isNotEmpty(userPermissions)){
                    userPermissionsIds.addAll(userPermissions);
                }
            }
            // 权限
            List<String> permissions = new ArrayList<>();
            // 菜单
            List<WilliamPermission> menus = new ArrayList<>();
            List<Integer> upids = new ArrayList<>(userPermissionsIds);
            WilliamPermissionExample williamPermissionExample = new WilliamPermissionExample();
            WilliamPermissionExample.Criteria criteria = williamPermissionExample.createCriteria();
            // 管理员
            if(2 != williamUser.getUserType()){
                criteria.andIdIn(upids);
                criteria.andStatusEqualTo(1);
            }
            List<WilliamPermission> permissionList = permissionMapper.selectByExample(williamPermissionExample);
            for (WilliamPermission williamPermission : permissionList) {
                if(1 == williamPermission.getType()){
                    menus.add(williamPermission);
                }
                if(2 == williamPermission.getType()){
                    String percode = williamPermission.getPercode();
                    permissions.add(percode);
                }
            }
            // 用户权限
            activerUser.setPermissions(permissions);
            // 用户角色
            activerUser.setRoles(currentRoleIds);
            // 惨淡
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
