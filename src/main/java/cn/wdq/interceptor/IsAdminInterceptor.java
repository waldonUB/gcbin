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

public class IsAdminInterceptor implements HandlerInterceptor {
    Logger logger= Logger.getLogger(LoginInterceptor.class);
    @Autowired
    LoginService login;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        //开日志试试
        logger.info("判断是否为管理员拦截器");
        //不能放在全局变量，会有缓存
        JSONArray jsonArray=new JSONArray();
        String session_string=null;
        JSONObject session_json=null;
        String user_name=null;
        String cuserid=null;
        int user_type=0;
        System.out.println("执行preHandle操作");
        String path=request.getContextPath();
        String url=request.getRequestURL().toString();
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
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        //如果prehandle不可以就加这里
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
