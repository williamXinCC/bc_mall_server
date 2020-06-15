package com.william.bc_mall_server.controller;

import cn.hutool.json.JSONUtil;
import com.william.bc_mall_server.redis.RedisService;
import com.william.bc_mall_server.service.WilliamLoginService;
import com.william.bcpojo.ActiverUser;
import com.william.bcpojo.bcreq.BcLoginReq;
import com.william.constant.Constant;
import com.william.constant.RespCodeAndMsg;
import com.william.pojo.Result;
import com.william.utils.BcCaptchaUtil;
import com.william.utils.Md5Util;
import com.william.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
 * @author xinchuang
 * @version v1.0
 * @date 2019/11/12 19:08
 * @since Copyright(c) 爱睿智健康科技
 */
@RestController
@RequestMapping(value = "/login")
@Slf4j
public class WilliamLoginController {

    @Autowired
    private RedisService redisService;

    @Autowired
    private WilliamLoginService williamLoginService;

//    @Autowired
//    private WilliamOperationLogService williamOperationLogService;

    @PostMapping(value = "/getCaptcha")
    public String getCode(){
        String s = BcCaptchaUtil.generateCode();
        String replace = s.toUpperCase().replace(",", "");
        redisService.stetStrTime(Constant.CAPTCHA + replace,"",120);
        return s;
    }


    @RequestMapping(value = "/getInfo")
    public Result getInfo(){
        Object principal = SecurityUtils.getSubject().getPrincipal();
        return Result.getResult(RespCodeAndMsg.LOGIN_SUCCESS,principal);
    }


    @RequestMapping(value = "/login")
    public Result bcLoginReq(@RequestBody BcLoginReq bcLoginReq,@RequestParam(value="isRememberMe", defaultValue="0") Integer isRememberMe, Model model){
//        boolean b = redisService.ishasKey(Constant.CAPTCHA + bcLoginReq.getCaptcha().toUpperCase());
//        if(!b){
//            return Result.getResult(RespCodeAndMsg.CAPTCH_ERROR);
//        }
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(bcLoginReq.getUserPhone(), bcLoginReq.getPassword());
        if (isRememberMe == 1) {
            usernamePasswordToken.setRememberMe(true);
        }
        Subject subject = SecurityUtils.getSubject();
        String token;
        try {
            subject.login(usernamePasswordToken);
            ActiverUser activerUser=(ActiverUser) subject.getPrincipal();
            redisService.setStr("user",JSONUtil.toJsonStr(activerUser));
            token = subject.getSession().getId().toString();
            // 存入session
            WebUtils.getSession().setAttribute("user",activerUser.getWilliamUser());
            //记录登陆日志
//            WilliamOperationLog williamOperationLog = new WilliamOperationLog();
//            williamOperationLog.set(activerUser.getWilliamUser().getUserName() + "-" + activerUser.getWilliamUser().getUserName());
//            williamOperationLog.setLoginIp(WebUtils.getRequest().getRemoteAddr());
//            williamOperationLog.setLoginTime(new Date());
//            williamOperationLogService.save(williamOperationLog);
            return Result.getResult(RespCodeAndMsg.LOGIN_SUCCESS,token);
        } catch (AccountException e) {
            // 用户不存在或已过期 (用户名不存在)
            return Result.getResult(RespCodeAndMsg.LOGIN_ERROR_NON_ACCOUNT,e.getMessage());
        } catch (CredentialsException e){
            // 用户名或密码错误 (密码错误)
            return Result.getResult(RespCodeAndMsg.LOGIN_ERROR_NON_PASS,e.getMessage());
        } catch (AuthenticationException e){
            // 请求错误
            return Result.getResult(RespCodeAndMsg.UNIFY_EXCEPTION);
        }
    }
}
