package com.william.bc_mall_server.service;


import com.william.bcpojo.vo.RoleVo;
import com.william.pojo.Result;

/**
 * @author xinchuang
 * @version v1.0
 * @date 2020/6/5 17:58
 * @since Copyright(c) 爱睿智健康科技
 */
public interface WilliamRoleService {

    Result getAllRoles(Integer page,Integer limit);

    void addRole(RoleVo roleVo);

    void updateByRoleId(RoleVo roleVo);

    void removeById(Integer roleId);
}
