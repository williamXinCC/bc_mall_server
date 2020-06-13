package com.william.bc_mall_server.service.impl;

import com.william.bc_mall_server.mapper.StatisticsMapper;
import com.william.bc_mall_server.service.StatisticsService;
import com.william.bcpojo.echarts.BaseStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xinchuang
 * @version v1.0
 * @date 2019/12/6 11:06
 * @since Copyright(c) 爱睿智健康科技
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {


    @Autowired
    private StatisticsMapper statisticsMapper;

    /**
     * 区域统计
     * @author     xinchuang
     * @param year :
     * @return : java.util.List<com.william.pojo.echarts.CustomerAreaStatistics>
     */
    @Override
    public List<BaseStatistics> getCustomerAreaStatisticsData(String year) {

        return statisticsMapper.getCustomerAreaStatisticsData(year);
    }

    /**
     * 年度业绩统计
     * @author     xinchuang
     * @param year :
     * @return : java.util.List<java.lang.Double>
     */
    @Override
    public List<Double> getCpYearGradeStatistics(String year) {
        return statisticsMapper.getCpYearGradeStatistics(year);
    }
}
