package cn.wdq.service;

import cn.wdq.entities.UserInfo;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

/**
 * 用户登录相关操作
 * @author waldon
 * */
public interface LoginService {
	/**
	 * 登录验证
	 * @param userInfo 用户信息 include:1.cuserid 用户主键 2.user_name 用户名
	 * @return 该用户的信息
	 * */
	List<UserInfo> login(UserInfo userInfo);
	/**
	 * 检查是否有同名用户
	 * @param json 用户信息 include:1.cuserid 用户主键 2.user_name 用户名
	 * @return 该用户名是否存在的状态 1 存在 0 不存在
	 * */
	int haveSame(JSONObject json);
	/**
	 * 注册新用户
	 * @param json 用户信息 include:1.cuserid 用户主键 2.user_name 用户名
	 * @return 注册是否成功的状态 1 成功 0失败
	 * */
	int register(JSONObject json);
	/**
	 * 强制注销用户
	 * @param user_name 用户名
	 * */
	void forceLogout(HttpServletRequest request, String user_name) throws SQLException;
	/**
	 * 主动注销用户
	 * @param user_name 用户名
	 * */
	void autoLogout(String user_name) throws SQLException;
	/**
	 * 插入一个唯一的cuserid
	 * @param userInfo 用户信息 include:1.cuserid 用户主键 2.user_name 用户名
	 * */
	void insertLoginInfo(UserInfo userInfo,HttpServletRequest request) throws SQLException;
	/**
	 * 修改密码
	 * @param user_name 用户名
	 * @param password 用户密码
	 * */
	void editPassword(String user_name, String password) throws SQLException;
	/**
	 * 上传头像以base64码的数据保存到数据库
	 * @param img_base64 base64格式的头像数据
	 * @param user_name 用户名
	 * */
	void setHeadImg(String img_base64, String user_name) throws SQLException;
	/**
	 * 更新最近登录时间
	 * @param user_name 用户名
	 * */
	void updateLastTime(String user_name);
	/**
	 * 增加登录历史用户
	 * @param userInfo 登录用户信息
	 * @return 增加是否成功的状态 1 成功 0失败
	 * */
	int addLoginHistory(UserInfo userInfo);
	/**
	 * 增加登录历史用户
	 * @return 登录历史表所有用户的信息
	 * */
	List<UserInfo> queryLogHis();
}
