package cn.wdq.mapping;

import java.util.List;

/**
 * 图表和地图
 * @author waldon
 * */
public interface IndexDAO {
    /**
     * 查询在线用户的信息
     * @return 在线用户的信息
     * */
    List  query_last();
    /**
     * 查询本年用户的活跃度
     * @return 本年用户的活跃度
     * */
    List  query_time();
    /**
     * 查询所有用户的地区分布
     * @return 所有用户的地区分布
     * */
    List  query_map();
}
