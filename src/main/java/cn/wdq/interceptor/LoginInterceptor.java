package cn.wdq.interceptor;

import cn.wdq.service.LoginService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 * 登录拦截器(好久之前的代码，先不动)
 * @author waldon
 * */
public class LoginInterceptor implements HandlerInterceptor{
	Logger logger= Logger.getLogger(LoginInterceptor.class);
	@Autowired
	LoginService login;
	String jdbc="com.mysql.jdbc.Driver";
	String connection="jdbc:mysql://localhost:3306/db_stanley";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String user_name=null;
		String cuserid=null;
		PreparedStatement pst=null;
		ResultSet res=null;
		Connection con=null;
//		String path=request.getContextPath();
//		String url=request.getRequestURL().toString();
		if(request.getSession().getAttribute("sessionKey")!=null){
			String session_string=(String)request.getSession().getAttribute("sessionKey");
			JSONObject session_json=JSONObject.parseObject(session_string);
			user_name=session_json.getString("user_name");
			cuserid=session_json.getString("cuserid");
		}
		login.deleteLoginInfo(request, response, user_name, 0);//删除了当前用户不在线的信息
		String sb=" select * from login_info where cuserid='"+cuserid+"' and is_online=1 ";//用户名还是存在的，但是SessionID不可能相同
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_stanley","root","123456");
			pst=con.prepareStatement(sb.toString());
			res=pst.executeQuery();
			while(res.next()){
				//response.sendRedirect("/PetsCT/home/index.html");
				return true;
			}
		} catch (Exception e) {
			System.out.println("空指针异常");
		}finally {
			res.close();
			pst.close();
			con.close();
		}
		response.sendRedirect("/PetsCT/login_error.html");
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
