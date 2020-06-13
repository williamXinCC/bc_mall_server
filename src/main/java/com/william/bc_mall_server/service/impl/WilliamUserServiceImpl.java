package com.william.bc_mall_server.service.impl;

import cn.hutool.json.JSONUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.william.bc_mall_server.mapper.WilliamRoleMapper;
import com.william.bc_mall_server.mapper.WilliamUserMapper;
import com.william.bc_mall_server.mapper.WilliamUserRoleMapper;
import com.william.bc_mall_server.redis.RedisService;
import com.william.bc_mall_server.service.WilliamRoleService;
import com.william.bc_mall_server.service.WilliamUserService;
import com.william.bcconstant.BcConsts;
import com.william.bcpojo.ActiverUser;
import com.william.bcpojo.WilliamUser;
import com.william.bcpojo.WilliamUserRoleExample;
import com.william.bcpojo.bcreq.UpdatePasswordReq;
import com.william.bcpojo.bcresp.UserInfoResp;
import com.william.bcpojo.bcresp.UserRoleResp;
import com.william.bcpojo.vo.UserVo;
import com.william.constant.RespCodeAndMsg;
import com.william.pojo.Result;
import com.william.utils.IdSaltUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;


/**
 * @author xinchuang
 * @version v1.0
 * @date 2020/6/9 16:40
 * @since Copyright(c) 爱睿智健康科技
 */
@Service
public class WilliamUserServiceImpl implements WilliamUserService {

    @Autowired
    private WilliamUserMapper williamUserMapper;

    @Autowired
    private WilliamRoleMapper roleMapper;

    @Autowired
    private WilliamUserRoleMapper williamUserRoleMapper;

    @Autowired
    private RedisService redisService;

    @Override
    public WilliamUser getUserByUid(String uid) {
        return williamUserMapper.selectByPrimaryKey(uid);
    }

    @Override
    public WilliamUser getUserByPhone(String phone) {
        return williamUserMapper.getUserByPhone(phone);
    }

    @Override
    public Result getAllUser(UserVo userVo) {
        PageHelper.startPage(userVo.getPage(), userVo.getLimit());
        List<UserInfoResp> allUser = williamUserMapper.getAllUser();
        PageInfo<UserInfoResp> pageInfo = new PageInfo<>(allUser);
        long total = pageInfo.getTotal();
        return new Result(total,allUser);
    }

    @Override
    public void addUser(WilliamUser williamUser) {
         williamUserMapper.insertSelective(williamUser);
    }

    @Override
    public void updateById(WilliamUser williamUser) {
        williamUserMapper.updateByPrimaryKeySelective(williamUser);
    }

    @Override
    public WilliamUser getById(String userId) {
        return williamUserMapper.selectByPrimaryKey(userId);
    }

    @Override
    public void deleteUserById(String userId) {
        WilliamUser williamUser = new WilliamUser();
        williamUser.setUserId(userId);
        williamUser.setStatus(2);
        this.updateById(williamUser);
    }

    @Override
    public Result updatePwd(UpdatePasswordReq updatePasswordReq) {
        WilliamUser williamUser = this.getById(updatePasswordReq.getUserId());
        // salt
        ByteSource salt = IdSaltUtils.getSalt(williamUser.getUserPhone(), williamUser.getUserId());
        String oldPassword =  new Md5Hash(updatePasswordReq.getOldPassword(), salt, 2).toString();
        if(!Objects.equals(oldPassword,williamUser.getUserPassword())){
            return new Result(RespCodeAndMsg.UNIFY_EXCEPTION.getCode(),"密码输入错误!");
        }
        if(!Objects.equals(updatePasswordReq.getNewPassword(),updatePasswordReq.getRenewPassword())){
            return new Result(RespCodeAndMsg.UNIFY_EXCEPTION.getCode(),"两次输入密码不一致,请重新输入!");
        }
        williamUser.setSalt(salt.toString());
        williamUser.setUserPassword(new Md5Hash(updatePasswordReq.getNewPassword(), salt, 2).toString());
        this.updateById(williamUser);
        return Result.getResult(RespCodeAndMsg.OPERATE_SUCCESS);
    }

    @Override
    public Result resetPwd(String userId) {
        WilliamUser williamUser = new WilliamUser();
        ByteSource salt = IdSaltUtils.getSalt(williamUser.getUserPhone(), williamUser.getUserId());
        williamUser.setUserId(userId);
        williamUser.setUserPassword(new Md5Hash(BcConsts.DEFAULT_PASSWORD, salt, 2).toString());
        williamUserMapper.updateByPrimaryKeySelective(williamUser);
        return Result.getResult(RespCodeAndMsg.OPERATE_SUCCESS);
    }

    /**
     * 初始化角色
     * @author     xinchuang
     * @param userId :
     * @return : com.william.pojo.Result
     */
    @Override
    public Result initRoleByUserId(String userId) {
        // 1 查询所有可用的角色
        List<UserRoleResp> allRoles = roleMapper.getAllRoles();
        // 2 查当前用户角色
        List<UserRoleResp> userRoles = roleMapper.getByUserId(userId);
        for (UserRoleResp allRole : allRoles) {
            String roleCode = allRole.getRoleCode();
            allRole.setLAY_CHECKED(false);
            for (UserRoleResp userRole : userRoles) {
                if(Objects.equals(userRole.getRoleCode(),roleCode)){
                    allRole.setLAY_CHECKED(true);
                }
            }
        }
        return new Result(Long.valueOf(allRoles.size()), allRoles);
    }

    @Override
    public void saveUserRole(String userId, String[] roleCodes) {
        // 先删除再批量保存
        williamUserRoleMapper.deleteByUserId(userId);
        // rolesCode为null 删除所有角色
        if(Objects.nonNull(roleCodes) && roleCodes.length > 0){
            String user = redisService.getStr("user");
            ActiverUser activerUser = JSONUtil.toBean(user, ActiverUser.class);
            WilliamUser williamUser = activerUser.getWilliamUser();
            williamUserRoleMapper.inserBatch(userId,Arrays.asList(roleCodes),williamUser.getUserId(),new Date());
        }
    }

    @Override
    public WilliamUser getUserById(String userId) {
        return williamUserMapper.selectByPrimaryKey(userId);
    }
}
