package com.william.bc_mall_server.service;

import com.william.bcpojo.vo.AttrVo;
import com.william.pojo.Result;

/**
 * @author xinchuang
 * @version v1.0
 * @date 2020/6/23 11:30
 * @since Copyright(c) 爱睿智健康科技
 */
public interface AttrService {

    void addAttr(AttrVo attrVo);

    Result getAttrListByCondition(AttrVo attrVo);

    void deleteAttrById(Long aLong);

    void updateAttr(AttrVo attrVo);
}
