package com.william.bc_mall_server.mapper;


import com.william.bcpojo.echarts.BaseStatistics;

import java.util.List;

/**
 * @author xinchuang
 * @version v1.0
 * @date 2019/12/6 11:08
 * @since Copyright(c) 爱睿智健康科技
 */
public interface StatisticsMapper {

    List<BaseStatistics> getCustomerAreaStatisticsData(String year);

    List<Double> getCpYearGradeStatistics(String year);
}
