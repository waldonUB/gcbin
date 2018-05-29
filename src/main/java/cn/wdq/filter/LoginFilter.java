package cn.wdq.filter;

import cn.wdq.service.LoginService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {
	@Autowired
	LoginService login;
	String jdbc="com.mysql.jdbc.Driver";
	String connection="jdbc:mysql://localhost:3306/db_stanley";
	JSONArray jsonArray=new JSONArray();
	String session_string=null;
	JSONObject session_json=null;
	String user_name=null;
	String cuserid=null;
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest servletRequest,ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		final HttpServletRequest request = (HttpServletRequest) servletRequest;
		final HttpServletResponse response = (HttpServletResponse) servletResponse;
		String session_str=(String)request.getSession().getAttribute("sessionKey");
		System.out.println("执行doFilter操作");
		/*Runnable runable = new Runnable() {//继承thread类试试，不增加那么多线程
			@Override
			public void run() {
				System.out.println("子线程执行任务，当前时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()));
			}
		};
		try {
			System.out.println("主线程启动子线程时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			scheduleThread(5L, 30, runable);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		filterChain.doFilter(request,response);
	}
	static void scheduleThread(Long duration,Integer timeInterval,Runnable runnable) throws InterruptedException{
		//TimeUnit.SECONDS.sleep(duration);

		final Runnable interiorRun=runnable;
		final Integer interiorTimeInterval=timeInterval;
		/*new Thread(new Runnable() {
			@Override
			public void run() {
				while (true){
					interiorRun.run();
					try {
						TimeUnit.SECONDS.sleep(interiorTimeInterval);
					}catch (InterruptedException e){
						e.printStackTrace();
					}
				}

			}
		}).start();*/
	}
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
