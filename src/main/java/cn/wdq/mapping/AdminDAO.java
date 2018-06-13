package cn.wdq.mapping;

import java.util.List;

/**
 * 管理员操作
 * @author waldon
 * */
public interface AdminDAO {
    /**
     * 查询不同状态下的用户信息列表
     * @param userType 状态类型
     * @param page 当前页数
     * @return 用户信息列表
     * */
    List queryUserType(String userType, int page);
    /**
     * 查询不同状态下的页数
     * @return 页数
     * */
    int queryAllPage();
    /**
     * 用户名称的模糊查询
     * @param user_name 用户名
     * @return 符合该关键字的用户信息列表
     * */
    List querySearch(String user_name);
    /**
     * 删除用户
     * @param user_name 用户名
     * @return 成功的状态 1 成功 0失败
     * */
    int deleteCustomer(String user_name);
    /**
     * 删除在线用户表中的用户
     * @param user_name 用户名
     * @return 成功的状态 1 成功 0失败
     * */
    int kicking(String user_name);
}
