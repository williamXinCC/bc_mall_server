package com.william.bc_mall_server.exception;

import com.william.exception.ResponseException;
import com.william.pojo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * 统一异常处理
 * @author     xinchuang
 * @date       2018/11/28 19:00
 * @version    v1.0
 * @since      @Copyright(c) 爱睿智健康科技(北京)有限公司
 *
 */
@ControllerAdvice
public class ExceptionHandle {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 自定义异常
     * @author     xinchuang
     * @param request :
     * @param e :
     * @return : com.ismarthealth.app.common.pojo.ApiAppResponse.Result
     */
    @ExceptionHandler(ResponseException.class)
    @ResponseBody
    public Result runtimeException(HttpServletRequest request, ResponseException e) {
        log(e,request);
        return new Result(e.getCode(),e.getMessage());
    }

//    /**
//     * 数据校验异常处理
//     * @author     xinchuang
//     * @param request :
//     * @param t :
//     * @return : com.ismarthealth.app.common.pojo.ApiAppResponse.Result
//     */
//    @ExceptionHandler(ValidationException.class)
//    @ResponseBody
//    public Result paramException(HttpServletRequest request, ValidationException t) {
//        Result error = Result.errorMsg(t.getMessage());
//        log(t,request);
//        return error;
//    }
//
//
//    /**
//     * 全局异常处理
//     * @author     xinchuang
//     * @param request :
//     * @param e :
//     * @return : com.ismarthealth.app.common.pojo.ApiAppResponse.Result
//     */
//    @ExceptionHandler(value = Exception.class)
//    @ResponseBody
//    public Result jsonHandler(HttpServletRequest request, Exception e) throws Exception {
//        Result r = Result.error(AppResponseCode.UNIFY_EXCEPTION);
//        r.setRemark(e.getMessage());
//        log(e,request);
//        return r;
//    }


    /**
     * 异常日志
     * @author     xinchuang
     * @param ex :
     * @param request :
     * @return : void
     */
    private void log(Exception ex, HttpServletRequest request) {
        logger.error("************************异常开始*******************************");
        logger.error(String.valueOf(ex));
        logger.error("请求地址：" + request.getRequestURL());
        Enumeration enumeration = request.getParameterNames();
        logger.error("请求参数");
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement().toString();
            logger.error(name + "---" + request.getParameter(name));
        }
        StackTraceElement[] error = ex.getStackTrace();
        for (StackTraceElement stackTraceElement : error) {
            logger.error(stackTraceElement.toString());
        }
        logger.error("************************异常结束*******************************");
    }
}
