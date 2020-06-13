package com.william.bc_mall_server.controller;


import com.william.bc_mall_server.mapper.WilliamUserRoleMapper;
import com.william.bc_mall_server.service.WilliamUserService;
import com.william.bcpojo.WilliamUser;
import com.william.bcpojo.bcreq.UpdatePasswordReq;
import com.william.bcpojo.bcresp.UserInfoResp;
import com.william.bcpojo.vo.UserVo;
import com.william.constant.RespCodeAndMsg;
import com.william.pojo.Result;
import com.william.utils.IdSaltUtils;
import com.william.utils.ObjectId;
import com.william.utils.WebUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mybatisPlusGenerator
 * @since 2019-11-20
 */
@RestController
@RequestMapping("/william-user")
public class WilliamUserController {

    @Autowired
    private WilliamUserService userService;
//
//    @Autowired
//    private IWilliamDeptService deptService;
//
//    @Autowired
//    private IWilliamRoleService roleService;
//
//    @Autowired
//    private IWilliamUserRoleService userRoleService;
//
//
//    @GetMapping(value = "/getAllDeptManager")
//    public DataGridView getAllDeptManager(String deptId){
//        // 查角色为经理
//        QueryWrapper<WilliamUserRole> userRoleQueryWrapper = new QueryWrapper<>();
//        userRoleQueryWrapper.eq("role_id", Consts.MANAGER_ROLE_ID);
//        List<WilliamUserRole> userRoleList  = userRoleService.list(userRoleQueryWrapper);
//        List<String> uidList = new ArrayList<>();
//        for (WilliamUserRole williamUserRole : userRoleList) {
//            uidList.add(williamUserRole.getUserId());
//        }
//        // 查部门下用户
//        QueryWrapper<WilliamUser> queryWrapper=new QueryWrapper<>();
//        queryWrapper.eq(StringUtils.isNotBlank(deptId), "dept_id", deptId);
//        queryWrapper.eq("status", Constant.AVAILABLE_TRUE);
//        queryWrapper.in("id",uidList);
//        List<WilliamUser> list = this.userService.list(queryWrapper);
//        return new DataGridView(list);
//    }
//
//
    /**
     *  查所有用户 分页 条件
     */
    @RequestMapping("/getAllUser")
    public Result getAllUser(UserVo userVo) {
        return userService.getAllUser(userVo);
    }
//
//
//    /**
//     * 加载最大的排序码
//     * @return
//     */
//    @RequestMapping("/getUserMaxOrderNum")
//    public Map<String,Object> getUserMaxOrderNum(){
//        Map<String, Object> map=new HashMap<String, Object>();
//        QueryWrapper<WilliamUser> queryWrapper=new QueryWrapper<>();
//        queryWrapper.orderByDesc("sort");
//        IPage<WilliamUser> page=new Page<>(1, 1);
//        List<WilliamUser> list = this.userService.page(page, queryWrapper).getRecords();
//        if(list.size()>0) {
//            map.put("value", list.get(0).getSort()+1);
//        }else {
//            map.put("value", 1);
//        }
//        return map;
//    }
//
//
//    /**
//     * 根据部门ID查询用户
//     */
//    @RequestMapping("/getUsersByDeptId")
//    public DataGridView getUsersByDeptId(Integer deptid) {
//        QueryWrapper<WilliamUser> queryWrapper=new QueryWrapper<>();
//        queryWrapper.eq(deptid!=null, "deptid", deptid);
//        queryWrapper.eq("status", Constant.AVAILABLE_TRUE);
//        queryWrapper.eq("type", Constant.USER_TYPE_NORMAL);
//        List<WilliamUser> list = this.userService.list(queryWrapper);
//        return new DataGridView(list);
//    }
//
//
//    /**
//     * 把用户名转成拼音
//     */
//    @RequestMapping("/changeChineseToPinyin")
//    public Map<String,Object> changeChineseToPinyin(String username){
//        Map<String,Object> map=new HashMap<>();
//        if(null!=username) {
//            map.put("value", PinyinUtils.getPingYin(username));
//        }else {
//            map.put("value", "");
//        }
//        return map;
//    }
//
    /**
     * 添加用户
     */
    @RequestMapping("/addUser")
    public Result addUser(WilliamUser williamUser) {
        String uid = ObjectId.get();
        williamUser.setUserId(uid);
//        williamUser.setUserType(Constant.USER_TYPE_NORMAL);
        // 入职时间
//        williamUser.setInCompanyDate(new Date());
        ByteSource salt = IdSaltUtils.getSalt(williamUser.getUserName(), uid);
        williamUser.setSalt(salt.toString());
        williamUser.setUserPassword(new Md5Hash(williamUser.getUserPassword(), salt, 2).toString());
        userService.addUser(williamUser);
        return Result.getResult(RespCodeAndMsg.ADD_SUCCESS);
    }


    /**
     * ID查询一个用户
     */
    @RequestMapping("/getUserById")
    public Result getUserById(String userId) {
        WilliamUser williamUser = userService.getUserById(userId);
        return Result.getResult(RespCodeAndMsg.OPERATE_SUCCESS,williamUser);
    }


    /**
     * 修改用户
     */
    @RequestMapping("/updateUser")
    public Result updateUser(WilliamUser williamUser) {
        userService.updateById(williamUser);
        WilliamUser byId = userService.getById(williamUser.getUserId());
        WebUtils.getSession().setAttribute("user",byId);
        return Result.getResult(RespCodeAndMsg.UPDATE_SUCCESS);
    }


    /**
     * 删除用户
     */
    @RequestMapping("/deleteUserById")
    public Result deleteUserById(String userId) {
        userService.deleteUserById(userId);
        return Result.getResult(RespCodeAndMsg.UPDATE_SUCCESS);
    }

    /**
     * 重置默认密码为 123456
     * @author     xinchuang
     * @param userId :
     * @return : com.william.pojo.Result
     */
    @RequestMapping("/resetPwd")
    public Result resetPwd(String userId){
        return userService.resetPwd(userId);
    }

    /**
     * 修改用户密码
     */
    @RequestMapping("/updatePwd")
    public Result updatePwd(UpdatePasswordReq updatePasswordReq) {
        return userService.updatePwd(updatePasswordReq);
    }


    /**
     * 根据用户ID查询角色并选中已拥有的角色
     */
    @RequestMapping("/initRoleByUserId")
    public Result initRoleByUserId(String userId) {
        return userService.initRoleByUserId(userId);
    }

    /**
     * 保存用户和角色的关系
     */
    @RequestMapping("/saveUserRole")
    public Result saveUserRole(String userId, String[] roleCodes) {
        userService.saveUserRole(userId,roleCodes);
        return Result.getResult(RespCodeAndMsg.ADD_SUCCESS);
    }
}
