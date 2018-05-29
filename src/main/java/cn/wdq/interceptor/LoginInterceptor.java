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

public class LoginInterceptor implements HandlerInterceptor{
	Logger logger= Logger.getLogger(LoginInterceptor.class);
	@Autowired
	LoginService login;
	String jdbc="com.mysql.jdbc.Driver";
	String connection="jdbc:mysql://localhost:3306/db_stanley";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//开日志试试
		logger.error("info_preHandle日志输出");
		logger.warn("error_preHandle日志输出");
		logger.info("warn_preHandle日志输出");
		logger.debug("nothing_preHandle日志输出");
		//不能放在全局变量，会有缓存
		JSONArray jsonArray=new JSONArray();
		String session_string=null;
		JSONObject session_json=null;
		String user_name=null;
		String cuserid=null;
		System.out.println("执行preHandle操作");
		String path=request.getContextPath();
		String url=request.getRequestURL().toString();
		PreparedStatement pst=null;//一定要在finally关闭
		ResultSet res=null;
		Connection con=null;
		/*if(url.startsWith("http://localhost:8080/PetsCT/home/login_error.html")){
			response.sendRedirect("/PetsCT/login.html");
			return false;
		}*/
		if(request.getSession().getAttribute("sessionKey")!=null){
			session_string=(String)request.getSession().getAttribute("sessionKey");
			session_json=JSONObject.parseObject(session_string);
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
		// TODO Auto-generated method stub
		System.out.println("执行postHandle操作");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("执行afterCompletion操作");
		
	}

}
