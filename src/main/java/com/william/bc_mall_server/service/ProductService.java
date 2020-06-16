package com.william.bc_mall_server.service;

import com.william.bcpojo.vo.ProductVo;
import com.william.pojo.Result;
import com.william.pojo.WilliamProduct; /**
 * @author xinchuang
 * @version v1.0
 * @date 2020/6/16 15:01
 * @since Copyright(c) 爱睿智健康科技
 */
public interface ProductService {

    void addProduct(WilliamProduct williamProduct);

    Result getProductList(ProductVo productVo);
}
