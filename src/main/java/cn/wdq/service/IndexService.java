package cn.wdq.service;

import java.util.List;

/**
 * 首页数据界面展现
 * @author waldon
 * */
public interface IndexService {
    /**
     * 查询最近登录，头像
     * @return 已上线用户的信息
     * */
    List query_last();
    /**
     * 查询登录时间分布,柱状图
     * @return 各个时间段用户的人数
     */
    List query_time();
    /**
     * 查询全国用户地区分布
     * @return 各个地区人数汇总
     * */
    List query_map();
}
