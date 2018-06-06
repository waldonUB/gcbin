package cn.wdq.service;

import cn.wdq.entities.ReturnModel;
import cn.wdq.entities.UserInfo;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public interface LoginService {
	/**
	 * 登录验证
	 * */
	List<UserInfo> login(UserInfo userInfo);
	/**
	 * 检查是否有同名用户
	 * */
	boolean has_same(JSONObject json);
	/**
	 * 注册新用户
	 * */
	int register(JSONObject json);
	/**
	 * 强制注销用户
	 * */
	void forceLogout(HttpServletRequest request, HttpServletResponse response, String user_name) throws SQLException;
	/**
	 * 主动注销用户
	 * */
	void autoLogout(String user_name) throws SQLException;
	/**
	 * 插入一个唯一的cuserid
	 * */
	void insertLoginInfo(HttpServletRequest request, HttpServletResponse response, String user_name) throws SQLException;
	/**
	 * 根据cuserid删除，或者把is_online=0的删除
	 * */
	void deleteLoginInfo(HttpServletRequest request, HttpServletResponse response, String name, int is_online) throws SQLException;
	/**
	 * 返回被强制下线信息
	 * */
	ReturnModel notLogin();
	/**
	 * 修改密码
	 * */
	void editPassword(String user_name, String password) throws SQLException;
	/**
	 * 上传头像以base64码的数据保存到数据库
	 * */
	void setHeadImg(String img_base64, String user_name) throws SQLException;
	/**
	 * 更新最近登录时间
	 * */
	void updateLasttime(String user_name);
}
