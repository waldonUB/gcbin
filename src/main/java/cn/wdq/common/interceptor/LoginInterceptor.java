package cn.wdq.common.interceptor;

import cn.wdq.service.CommonService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 登录拦截器(前端获取url地址作为Vue的watch属性来做第一层拦截)
 * @author waldon
 * */
public class LoginInterceptor implements HandlerInterceptor{
	private Logger logger= Logger.getLogger(LoginInterceptor.class);
	@Autowired
	CommonService commonService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
//		String path=request.getContextPath();
//		String url=request.getRequestURL().toString();
		String cuserid=request.getSession().getId();
		int onlineNum=commonService.loginInterceptor(cuserid);
		if(onlineNum>0){
			return true;
		}
		logger.info("interceptor preHandle:登录信息失效，返回登录页面");
		response.sendRedirect("/gcbin/login_error.html");
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView){
	}
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex){

	}

}
