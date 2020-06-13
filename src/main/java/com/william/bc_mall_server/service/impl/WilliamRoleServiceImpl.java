package com.william.bc_mall_server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.william.bc_mall_server.mapper.WilliamRoleMapper;
import com.william.bc_mall_server.service.WilliamRoleService;
import com.william.bcpojo.bcresp.UserRoleResp;
import com.william.bcpojo.vo.RoleVo;
import com.william.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author xinchuang
 * @version v1.0
 * @date 2020/6/5 18:01
 * @since Copyright(c) 爱睿智健康科技
 */
@Service
public class WilliamRoleServiceImpl implements WilliamRoleService {

    @Autowired
    private WilliamRoleMapper williamRoleMapper;

    @Override
    public Result getAllRoles(Integer page,Integer limit){
        if(page >= 0 && limit >= 0 ){
            PageHelper.startPage(page, limit);
            List<UserRoleResp> allRoles = williamRoleMapper.getAllRoles();
            PageInfo<UserRoleResp> pageInfo = new PageInfo<>(allRoles);
            long total = pageInfo.getTotal();
            return new Result(total,allRoles);
        }
        List<UserRoleResp> allRoles = williamRoleMapper.getAllRoles();
        PageInfo<UserRoleResp> pageInfo = new PageInfo<>(allRoles);
        long total = pageInfo.getTotal();
        return new Result(total,allRoles);
    }

    @Override
    public void addRole(RoleVo roleVo) {
        williamRoleMapper.insertSelective(roleVo);
    }

    @Override
    public void updateByRoleId(RoleVo roleVo) {
        williamRoleMapper.updateByPrimaryKey(roleVo);
    }

}
