package cn.wdq.mapping;

import cn.wdq.entities.UserInfo;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * 用户信息处理
 * @author waldon
 * */
public interface UserInfoDAO {
    /**
     * 用户登录验证
     * @param userInfo include:1.user_name 用户名 2.password 密码
     * @return 该用户的个人信息
     * */
    List<UserInfo> login(UserInfo userInfo);
    /**
     * 查询是否存在相同的用户名
     * @param json include:1.user_name 用户名
     * @return 该用户的个人信息
     * */
    List<UserInfo> hasSame(JSONObject json);
    /**
     * 踢用户下线
     * @param json include:1.user_name 用户名 2.password 密码
     * @return 是否注册成功的状态
     * */
    int register(JSONObject json);
    /**
     * 用户登录验证
     * @param user_name 用户名
     * @return 是否踢掉成功的状态
     * */
    int kicking(String user_name);
    /**
     * 更新最近上线时间
     * @param user_name 用户名
     * @return 是否更新成功的状态
     * */
    int updateLasttime(String user_name);
    /**
     * 删除该在线用户
     * @param cuserid 用户主键
     * @return 是否删除成功的状态
     * */
    int deleteOnline(String cuserid);
    /**
     * 删除所有登录用户
     * @return 是否删除成功的状态
     * */
    int clearAll();
}
