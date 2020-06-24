package com.william.bc_mall_server.controller;

import com.william.bc_mall_server.service.AttrService;
import com.william.bcpojo.vo.AttrVo;
import com.william.constant.RespCodeAndMsg;
import com.william.pojo.Result;
import com.william.pojo.WilliamBrand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author xinchuang
 * @version v1.0
 * @date 2020/6/22 15:13
 * @since Copyright(c) 爱睿智健康科技
 */
@RestController
@RequestMapping("/william-attr")
public class AttrController {

    @Autowired
    private AttrService attrService;


    /**
     * 删除属性
     * @author     xinchuang
     * @param attrVo :
     * @return : com.william.pojo.Result
     */
    @RequestMapping("/deleteAttrById")
    public Result deleteAttrById(AttrVo attrVo) {
        attrService.deleteAttrById(attrVo.getnId());
        return Result.getResult(RespCodeAndMsg.DELETE_SUCCESS);
    }


    /**
     * 修改属性
     * @author     xinchuang
     * @param attrVo :
     * @return : com.william.pojo.Result
     */
    @PostMapping(value = "/updateAttr")
    public Result updateAttr(AttrVo attrVo){
        attrService.updateAttr(attrVo);
        return Result.getResult(RespCodeAndMsg.UPDATE_SUCCESS);
    }

    /**
     * 获取属性列表
     * @author     xinchuang
     * @param attrVo :
     * @return : com.william.pojo.Result
     */
    @GetMapping(value = "/getAttrListByCondition")
    public Result getAttrListByCondition(AttrVo attrVo){
        return attrService.getAttrListByCondition(attrVo);
    }

    /**
     * 添加属性 规格/参数   规格包括颜色 尺寸等通用规格   参数包括屏幕尺寸 系统 网络等 举例笔记本硬件规格相同 参数不同
     * @author     xinchuang
     * @return : com.william.pojo.Result
     */
    @PostMapping(value = "/addAttr")
    public Result addAttr(AttrVo attrVo) {
        attrService.addAttr(attrVo);
        return Result.getResult(RespCodeAndMsg.ADD_SUCCESS);
    }
}
