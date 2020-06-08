package com.william.bc_mall_server.shiroConfig;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.william.bc_mall_server.service.WilliamMenuService;
import com.william.bc_mall_server.service.WilliamRoleService;
import com.william.bc_mall_server.service.WilliamUserService;
import com.william.bcpojo.ActiverUser;
import com.william.bcpojo.WilliamUser;
import com.william.constant.Constant;
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
    private WilliamMenuService menuService;

    @Autowired
    @Lazy
    private WilliamRoleService roleService;

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
        ActiverUser activerUser=(ActiverUser) principals.getPrimaryPrincipal();
        WilliamUser user = activerUser.getWilliamUser();
        List<Integer> permissions = activerUser.getPermissions();
        if(Objects.equals(user.getUserType(), Constant.USER_TYPE_SUPER)) {
            authorizationInfo.addStringPermission("*:*");
        }else {
            if(Objects.nonNull(permissions) && permissions.size()>0) {
                authorizationInfo.addStringPermissions(permissions);
            }
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
        String userName = token.getPrincipal().toString();
        // 查询用户
        QueryWrapper<WilliamUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",userName);
        WilliamUser williamUser = userService.getOne(queryWrapper);
        if(Objects.nonNull(williamUser)){
            ActiverUser activerUser = new ActiverUser();
            activerUser.setWilliamUser(williamUser);
            // uid + 角色 + 权限查询
            String uid = williamUser.getId();
            // 查询角色
            List<String> currentRoleIds = roleService.getUserRoleIdsByUid(uid);
            // 角色查询权限和菜单id
            Set<Integer> mids = new HashSet<>();
            for (String currentRoleId : currentRoleIds) {
               List<Integer> menuAndPIds =  roleService.getRoleMenuAndPIdsByRid(currentRoleId);
               mids.addAll(menuAndPIds);
            }
            List<WilliamMenu> list = new ArrayList<>();
            // 根据角色ID查询权限
            if(mids.size() > 0){
                // 查询用户permission
                QueryWrapper<WilliamMenu> menuQueryWrapper = new QueryWrapper<>();
                // 只查询菜单
                menuQueryWrapper.eq("type", Constant.TYPE_PERMISSION);
                menuQueryWrapper.eq("status" , Constant.AVAILABLE_TRUE);
                menuQueryWrapper.eq("show_flag",Constant.AVAILABLE_TRUE);
                menuQueryWrapper.in("id",mids);
                list = menuService.list(menuQueryWrapper);
            }
            List<String> permissions = new ArrayList<>();
            for (WilliamMenu williamMenu : list) {
                permissions.add(williamMenu.getCode());
            }
            activerUser.setPermissions(permissions);
            ByteSource credentialsSalt = IdSaltUtils.getSalt(williamUser.getUserName(), williamUser.getId());
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(activerUser, williamUser.getPassWord(), credentialsSalt, getName());
            Session session = SecurityUtils.getSubject().getSession();
            session.setAttribute("userSession",activerUser);
            return authenticationInfo;
        }
        return null;
    }

}
