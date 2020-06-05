//package com.william.bc_mall_server.controller;
//
//
//import com.william.constant.RespCodeAndMsg;
//import com.william.pojo.DataGridView;
//import com.william.pojo.Result;
//import com.william.pojo.WilliamSysLoginLog;
//import com.william.pojo.vo.SysLoginLogVo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * <p>
// *  前端控制器
// * </p>
// *
// * @author mybatisPlusGenerator
// * @since 2019-12-10
// */
//@RestController
//@RequestMapping("/william-sys-login-log")
//public class WilliamSysLoginLogController {
//
//
//    @Autowired
//    private IWilliamSysLoginLogService logInfoService;
//
//    /**
//     * 加载日志列表返回DataGridView
//     */
//    @GetMapping(value = "/getLoginLog")
//    public DataGridView getLoginLog(SysLoginLogVo logInfoVo) {
//        return logInfoService.getLoginLog(logInfoVo);
//    }
//
//    /**
//     * 删除日志
//     */
//    @RequestMapping("/deleteLogInfo")
//    public Result deleteLogInfo(WilliamSysLoginLog williamSysLoginLog) {
//        logInfoService.deleteLogInfo(williamSysLoginLog.getId());
//        return Result.getResult(RespCodeAndMsg.DELETE_SUCCESS);
//    }
//
//    /**
//     * 批量删除日志
//     */
//    @RequestMapping("deleteBatchLogInfo")
//    public Result deleteBatchLogInfo(SysLoginLogVo logInfoVo) {
//        this.logInfoService.deleteBatchLogInfo(logInfoVo.getIds());
//        return Result.getResult(RespCodeAndMsg.DELETE_SUCCESS);
//    }
//}
