package cn.wdq.interceptor;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 判断当前登录用户是否为管理员，防止普通用户跳到管理员界面
 * @author waldon
 * */
public class IsAdminInterceptor implements HandlerInterceptor {
    private Logger logger= Logger.getLogger(LoginInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        logger.info("判断是否为管理员拦截器");
        String session_string;
        JSONObject session_json;
        int user_type=0;
//        String path=request.getContextPath();
//        String url=request.getRequestURL().toString();
        if(request.getSession().getAttribute("sessionKey")!=null){
            session_string=(String)request.getSession().getAttribute("sessionKey");
            session_json=JSONObject.parseObject(session_string);
            user_type=session_json.getInteger("user_type");
        }
        if(user_type==0){//为普通用户则跳回用户首页
            response.sendRedirect("/PetsCT/home/user/userindex.html");
            return false;
        }else{
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e)  {

    }
}
