package com.william.bc_mall_server.service;

import com.william.bcpojo.WilliamUser;
import com.william.bcpojo.bcreq.UpdatePasswordReq;
import com.william.bcpojo.bcresp.UserInfoResp;
import com.william.bcpojo.vo.UserVo;
import com.william.pojo.Result;


/**
 * @author xinchuang
 * @version v1.0
 * @date 2020/6/5 17:58
 * @since Copyright(c) 爱睿智健康科技
 */
public interface WilliamUserService {

    WilliamUser getUserByUid(String userName);

    WilliamUser getUserByPhone(String phone);

    Result getAllUser(UserVo userVo);

    void addUser(WilliamUser williamUser);

    void updateById(WilliamUser williamUser);

    WilliamUser getById(String userId);

    void deleteUserById(String userId);

    Result updatePwd(UpdatePasswordReq updatePasswordReq);

    Result resetPwd(String id);

    Result initRoleByUserId(String id);

    void saveUserRole(String userId, String[] roleCodes);

    WilliamUser getUserById(String userId);
}
