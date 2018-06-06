package cn.wdq.service;

import cn.wdq.dao.UIDImpl;
import cn.wdq.entities.ReturnModel;
import cn.wdq.entities.UserInfo;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LoginServiceImpl implements LoginService{
	Logger logger=Logger.getLogger(LoginServiceImpl.class);
	String jdbc="com.mysql.jdbc.Driver";
	String connection="jdbc:mysql://localhost:3306/db_stanley";
	static UIDImpl uIDImpl;
	static{
		System.out.println("进入了login_static模块");
		ApplicationContext ctx=new ClassPathXmlApplicationContext("springmvc-servlet.xml");
		uIDImpl=ctx.getBean("uIDImpl",UIDImpl.class);
		uIDImpl.clearAll();
		/*Thread thread=new Thread(new Runnable() {
			@Override
			public void run() {
				while (true){
					try {
						*//*SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String last_time=simpleDateFormat.format(new Date());
						Thread.sleep(10000);
						System.out.println("当前时间："+last_time);*//*
						List<UserInfo> userInfos=uIDImpl.getAllUserInfo();
						System.out.println(userInfos);
						Thread.sleep(30000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		thread.start();*/
	}
	/**
	 * 获取所有用户信息的方法
	 * */
	@Override
	public List<UserInfo> login(UserInfo userInfo){
		/*list.clear();//清除list里面残留的数据
		StringBuffer sb=new StringBuffer(" select * from sm_user");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_stanley","root","123456");
			PreparedStatement pst=con.prepareStatement(sb.toString());
			ResultSet res=pst.executeQuery();
			while(res.next()){
				JSONObject json=new JSONObject();
				Map map=new HashMap();
				json.put("cuserid", res.getString("cuserid"));
				json.put("user_name", res.getString("user_name"));
				json.put("password", res.getString("password"));
				json.put("dr", 0);
				json.put("user_type", res.getObject("user_type"));
				json.put("is_lock", res.getObject("is_lock"));
				json.put("is_online", res.getObject("is_online"));
				map=json;
				list.add(map);
			}
			res.close();
			pst.close();
		} catch (Exception e) {
			System.out.println("空指针异常");
		}*/
		/*if(jsonArray!=null){
			list=jsonArray;
		}*/
		//访问数据库
		//loadLogin();
		List<UserInfo> userInfos=uIDImpl.login(userInfo);
		return userInfos;

	}
	/**
	 * 查询数据库是否有同名用户
	 * @param json user_name
	 * @return true 没有同名  false 存在同名
	 * */
	@Override
	public boolean has_same(JSONObject json){
		List list = uIDImpl.hasSame(json);
		if(list.isEmpty()){
			return true;
		}
		return false;
	}
	/**
	 * 注册新用户
	 * */
	@Override
	public int register(JSONObject json) {
		//ApplicationContext ctx=new ClassPathXmlApplicationContext("springmvc-servlet.xml");
		//UIDImpl uIDImpl=ctx.getBean("uIDImpl",UIDImpl.class);
		int i=uIDImpl.register(json);
		return i;
	}

	/**
	 * 强制注销用户更新用户信息
	 * */
	public void forceLogout(HttpServletRequest request,HttpServletResponse response,String user_name) throws SQLException {
		if(checkOnline(request,user_name)){
			Connection con=null;
			PreparedStatement pst=null;
			String sb=" update login_info set is_online=0 where user_name='"+user_name+"' ";
			try {
				Class.forName(jdbc);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			try {
				con = DriverManager.getConnection(connection,"root","123456");
				pst=con.prepareStatement(sb);
				pst.executeUpdate(sb);
				System.out.println("强制登陆成功");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}finally {
				pst.close();
				con.close();
			}
		}
	}
	/**
	 * 主动注销用户
	 * */
	public void autoLogout(String user_name) throws SQLException {
		String sb=" update login_info set is_online=0 where user_name='"+user_name+"' ";
		Connection con=null;
		PreparedStatement pst=null;
		try {
			Class.forName(jdbc);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			con = DriverManager.getConnection(connection,"root","123456");
			pst=con.prepareStatement(sb);
			pst.executeUpdate(sb);
			System.out.println("强制登陆成功");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}finally {
			pst.close();
			con.close();
		}
	}
	/**
	 * 检查是否在线
	 * */
	private boolean checkOnline(HttpServletRequest request,String user_name) throws SQLException {
		String session_string=null;
		JSONObject session_json=null;
		String cuserid=null;
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet res=null;
		if(request.getSession().getAttribute("sessionKey")!=null){
			session_string=request.getSession().getAttribute("sessionKey").toString();
			session_json=JSONObject.parseObject(session_string);
			cuserid=session_json.getString("cuserid");
		}
		String sb=" select * from login_info where user_name='"+user_name+"' and is_online=1 ";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(connection,"root","123456");
			pst=con.prepareStatement(sb);
			res=pst.executeQuery();
			if(res.next()){
				if(res.getString("cuserid").equals(cuserid)){
					return false;
				}
				return true;
			}
		} catch (Exception e) {
		}
		finally {
			res.close();
			pst.close();
			con.close();
		}
		return false;
	}
	/**
	 * 插入同样的用户，不同的cuserid，设置tookenid为当前cuserid，is_online为1
	 * */
	public void insertLoginInfo(HttpServletRequest request,HttpServletResponse response,String user_name) throws SQLException {
		String sb=" insert into login_info(cuserid,user_name,password,dr,user_type,is_lock,is_online,head_img,last_time) values(?,?,?,?,?,?,?,?,?)";
		HttpSession session=request.getSession();
		String cuserid=session.getId();
		//在这里直接删除登录信息表中的cuserid
		uIDImpl.deleteOnline(cuserid);
		String session_string=null;
		JSONObject session_json=null;
		Connection con=null;
		PreparedStatement pst=null;
		if(session.getAttribute("sessionKey")!=null){
			session_string=session.getAttribute("sessionKey").toString();
			session_json=JSONObject.parseObject(session_string);
			user_name=session_json.getString("user_name");
			String password=session_json.getString("password");
			int user_type=Integer.parseInt(session_json.getString("user_type"));
			int is_lock=Integer.parseInt(session_json.getString("is_lock"));
			int is_online=Integer.parseInt(session_json.getString("is_online"));
			String head_img=session_json.getString("head_img");
			//int dr=Integer.parseInt(session_json.getString("dr"));
			int dr=0;
			session_json.put("cuserid", cuserid);
			session_json.put("user_name", user_name);
			session_json.put("password", password);
			session_json.put("dr", dr);
			session_json.put("user_type", user_type);
			session_json.put("is_lock", is_lock);
			session_json.put("is_online", 1);
			session_json.put("head_img",head_img);
			request.setAttribute("sessionKey",session_json.toString());
			try {
				//增加最近登录日期字段
				SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String last_time=simpleDateFormat.format(new Date());
				request.getSession().setAttribute("sessionKey", session_json.toString());
				Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection(connection,"root","123456");
				pst=con.prepareStatement(sb);
				pst.setString(1, cuserid);
				pst.setString(2, user_name);
				pst.setString(3, password);
				pst.setInt(4, dr);
				pst.setInt(5, user_type);
				pst.setInt(6, is_lock);
				pst.setInt(7, 1);
				pst.setString(8, head_img);
				pst.setString(9,last_time);
				pst.executeUpdate();
			} catch (Exception e) {
				System.out.println("插入数据库异常");
			}finally {
				pst.close();
				con.close();
			}
		}
	}
	/**
	 * 显示登陆异常信息
	 * */
	public ReturnModel notLogin(){
		ReturnModel model=new ReturnModel();
		model.setSuccess(false);
		model.setMessage("您已被强制下线");
		return model;
	}
	/**
	 * 根据cuserid删除，或者把is_online=0的删除
	 * */
	public void deleteLoginInfo(HttpServletRequest request, HttpServletResponse response,String name,int is_online) throws SQLException {
		String session_string=null;
		JSONObject session_json=null;
		String user_name="这个是假值";
		String cuserid=null;
		Connection con=null;
		PreparedStatement pst=null;
		if(request.getSession().getAttribute("sessionKey")!=null){
			session_string=(String)request.getSession().getAttribute("sessionKey");
			session_json=JSONObject.parseObject(session_string);
			user_name=session_json.getString("user_name");
			cuserid=session_json.getString("cuserid");
		}
		String sb=" delete  FROM login_info where USER_NAME='"+user_name+"' and is_online=0 ";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(connection,"root","123456");
			pst=con.prepareStatement(sb.toString());
			pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("空指针异常");
		}
		finally {
			pst.close();
			con.close();
		}
	}
	/**
	 * 修改密码
	 * */
	public void editPassword(String user_name,String password) throws SQLException {
		Connection con=null;
		PreparedStatement pst=null;
		String sb=" update sm_user set password='"+password+"' where user_name='"+user_name+"' ";
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(connection,"root","123456");
			pst=con.prepareStatement(sb.toString());
			pst.executeUpdate();
			//autoLogout(user_name);//主动注销用户
		}catch (Exception e){
			logger.error("异常："+e);
		}
		finally {
			pst.close();
			con.close();
		}
	}

	/**
	 * 上传头像以base64码的数据保存到数据库
	 * */
	public void setHeadImg(String img_base64,String user_name) throws SQLException {
		Connection con=null;
		PreparedStatement pst=null;
		String sb="update sm_user set head_img='"+img_base64+"' where user_name='"+user_name+"' ";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(connection,"root","123456");
			pst=con.prepareStatement(sb);
			pst.executeUpdate();
		}catch (Exception e){
			logger.error("上传头像异常："+e);
		}
		finally {
			pst.close();
			con.close();
		}
	}

	public void updateLasttime(String user_name) {
		//ApplicationContext ctx=new ClassPathXmlApplicationContext("springmvc-servlet.xml");
		//UIDImpl uIDImpl=ctx.getBean("uIDImpl",UIDImpl.class);
		int i=uIDImpl.updateLasttime(user_name);
		System.out.println(i);
	}
}
