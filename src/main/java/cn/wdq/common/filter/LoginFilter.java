package cn.wdq.common.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录用户信息的处理
 * @author waldon
 * */
public class LoginFilter implements Filter {
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest servletRequest,ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		final HttpServletRequest request = (HttpServletRequest) servletRequest;
		final HttpServletResponse response = (HttpServletResponse) servletResponse;
		filterChain.doFilter(request,response);
	}
	@Override
	public void init(FilterConfig arg0){
		
	}

}
