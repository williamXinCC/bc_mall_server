package com.william.bc_mall_server.service;

import com.william.bcpojo.WilliamMenu;

import java.util.List;
import java.util.Set; /**
 * @author xinchuang
 * @version v1.0
 * @date 2020/6/5 17:58
 * @since Copyright(c) 爱睿智健康科技
 */
public interface WilliamMenuService {

    List<WilliamMenu> getMenusByIds(Set<Integer> menuList,Integer type);
}
