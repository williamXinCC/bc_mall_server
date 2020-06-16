package com.william.bc_mall_server.controller.sys;

import com.william.bc_mall_server.service.StatisticsService;
import com.william.bcpojo.echarts.BaseStatistics;
import com.william.constant.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
@RequestMapping(value = "/william-statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;


    /**
     * 客户全国分布图
     * @author     xinchuang
     * @return : java.lang.String
     */
    @RequestMapping(value = "/toNationCustomerStatistics")
    public String toNationCustomerStatistics(){
        return Constant.TO_PAGE + "statistics/nationCustomerStatistics";
    }


    /**
     * 年度统计报告
     * @author     xinchuang
     * @return : java.lang.String
     */
    @RequestMapping(value = "/toCpYearGradeStatistics")
    public String toCpYearGradeStatistics(){
        return   Constant.TO_PAGE + "statistics/cpYearGradeStatistics";
    }


    /**
     * 年度销售统计
     * @author     xinchuang
     * @param year :
     * @return : java.util.List<java.lang.Double>
     */
    @RequestMapping(value = "/getCpYearGradeStatistics")
    @ResponseBody
    public List<Double> getCpYearGradeStatistics(@RequestParam(value = "year",required = false)String year){
        return statisticsService.getCpYearGradeStatistics(year);
    }




    /**
     * 跳转到区域统计
     * @author     xinchuang
     * @return : java.lang.String
     */
    @RequestMapping(value = "/toCustomerAreaStatistics")
    public String toCustomerAreaStatistics(){
        return Constant.TO_PAGE + "statistics/customerAreaStatistics";
    }


    /**
     * 区域统计客户数据
     * @author     xinchuang
     * @param year :
     * @return : java.util.List<com.william.pojo.echarts.BaseStatistics>
     */
    @RequestMapping(value = "/getCustomerAreaStatisticsData")
    @ResponseBody
    public List<BaseStatistics> getCustomerAreaStatisticsData(@RequestParam(value = "year",required = false) String year){
        return statisticsService.getCustomerAreaStatisticsData(year);
    }

}

