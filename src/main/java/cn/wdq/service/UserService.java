package cn.wdq.service;

import java.sql.SQLException;

public interface UserService {
    /**
     * 关注对方
     * */
    void followSb(String followName, String idolName) throws SQLException;
}
