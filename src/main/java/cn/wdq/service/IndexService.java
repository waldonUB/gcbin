package cn.wdq.service;

import java.util.List;

public interface IndexService {
    /**
     * 查询最近登录，头像
     * */
    public List query_last();
    /**
     * 查询登录时间分布,柱状图
     */
    public List query_time();
    /**
     * 查询全国用户地区分布
     * */
    public List query_map();
}
